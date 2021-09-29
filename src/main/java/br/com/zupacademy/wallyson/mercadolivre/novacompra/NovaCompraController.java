package br.com.zupacademy.wallyson.mercadolivre.novacompra;

import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class NovaCompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EmailSender emailSender;

    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    public String save(@RequestBody @Valid NovaCompraRequest request, @AuthenticationPrincipal Usuario usuario) {
        var produto = produtoRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto.not-found"));

        var estoqueDisponivel = produto.diminuirEstoque(request.getQuantidade());

        if (!estoqueDisponivel) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "produto.sem-estoque");
        }

        var compra = request.toModel(produto, usuario);
        compraRepository.save(compra);
        emailSender.compraRealizada(compra);
        return compra.getGateway().getGatewayPagamento().gerarPagamento(compra);
    }
}
