package br.com.zupacademy.wallyson.mercadolivre.produto.novaopiniao;

import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/{id}/opinioes")
    public NovaOpiniaoResponse save(@PathVariable Long id, @RequestBody @Valid NovaOpiniaoRequest request,
                                    @AuthenticationPrincipal Usuario usuario) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto.not-found"));

        Opiniao opiniao = request.toModel(produto, usuario);
        opiniaoRepository.save(opiniao);

        return opiniao.toDto();
    }
}
