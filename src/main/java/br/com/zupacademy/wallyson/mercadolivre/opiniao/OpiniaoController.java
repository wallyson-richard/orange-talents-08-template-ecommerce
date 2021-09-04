package br.com.zupacademy.wallyson.mercadolivre.opiniao;

import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    private final OpiniaoRepository opiniaoRepository;
    private final ProdutoRepository produtoRepository;

    public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public void save(@RequestBody @Valid NovaOpiniaoRequest request, @AuthenticationPrincipal Usuario usuario) {
        Opiniao opiniao = request.toModel(produtoRepository, usuario);
        opiniaoRepository.save(opiniao);
    }
}
