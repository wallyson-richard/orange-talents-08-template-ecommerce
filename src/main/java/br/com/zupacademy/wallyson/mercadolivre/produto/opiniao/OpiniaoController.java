package br.com.zupacademy.wallyson.mercadolivre.produto.opiniao;

import br.com.zupacademy.wallyson.mercadolivre.exceptionhandler.ErrorResponse;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    private final OpiniaoRepository opiniaoRepository;
    private final ProdutoRepository produtoRepository;

    public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/{id}/opinioes")
    public ResponseEntity<?> save(@PathVariable Long id, @RequestBody @Valid NovaOpiniaoRequest request,
                               @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoSaved = produtoRepository.findById(id);

        if (produtoSaved.isEmpty()) {
            ErrorResponse error = new ErrorResponse("produto", "Produto informado não existe");
            return ResponseEntity.badRequest().body(error);
        }

        produtoSaved.ifPresent(produto -> {
            Opiniao opiniao = request.toModel(produto, usuario);
            opiniaoRepository.save(opiniao);
        });

        return ResponseEntity.ok().build();
    }
}
