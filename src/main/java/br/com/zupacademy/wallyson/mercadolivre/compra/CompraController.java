package br.com.zupacademy.wallyson.mercadolivre.compra;

import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;
    private final EmailSender emailSender;

    public CompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository, EmailSender emailSender) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.emailSender = emailSender;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    public String save(@RequestBody @Valid NovaCompraRequest request, @AuthenticationPrincipal Usuario usuario) {
        var produtoDaCompra = produtoRepository.findById(request.getProductId());
        var estoqueDisponivel = produtoDaCompra.get().diminuirEstoque(request.getQuantidade());

        if (!estoqueDisponivel) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Quantidade informada, maior que a quantidade que o produto possui em estoque");
        }

        var compra = request.toModel(produtoDaCompra.get(), usuario);
        compra = compraRepository.save(compra);
        emailSender.compraRealizada(compra);
        return compra.getGateway().getGatewayPagamento().gerarPagamento(compra);
    }
}
