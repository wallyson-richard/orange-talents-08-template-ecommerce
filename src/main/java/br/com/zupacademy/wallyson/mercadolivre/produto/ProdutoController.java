package br.com.zupacademy.wallyson.mercadolivre.produto;

import br.com.zupacademy.wallyson.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.wallyson.mercadolivre.produto.imagem.NovasImagensRequest;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final Uploader uploader;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
                             Uploader uploader) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.uploader = uploader;
    }

    @PostMapping
    public void save(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.toModel(categoriaRepository, usuario);
        produtoRepository.save(produto);
    }

    @PostMapping("/{id}/imagens")
    public void adicionarImagens(@PathVariable Long id, @Valid NovasImagensRequest request,
                                 @AuthenticationPrincipal Usuario usuario) {
        Set<String> urls = uploader.enviar(request.getImagens());

        Produto produto = produtoRepository.findById(id)
                .map(x -> {
                    lancarExceptionSeProdutoNaoPertenceAoUsuario(x, usuario);
                    x.adicionarImagem(urls);
                    return x;
                })
                .orElseThrow();
        produtoRepository.save(produto);
    }

    private void lancarExceptionSeProdutoNaoPertenceAoUsuario(Produto produto, Usuario usuario) {
        if (!produto.pertenceAoUsuario(usuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
