package br.com.zupacademy.wallyson.mercadolivre.produto.novoproduto;

import br.com.zupacademy.wallyson.mercadolivre.novacategoria.CategoriaRepository;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class NovoProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public void save(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.toModel(categoriaRepository, usuario);
        produtoRepository.save(produto);
    }
}
