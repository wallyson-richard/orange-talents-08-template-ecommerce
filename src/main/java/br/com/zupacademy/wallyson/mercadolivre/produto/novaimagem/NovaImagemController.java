package br.com.zupacademy.wallyson.mercadolivre.produto.novaimagem;

import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class NovaImagemController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Uploader uploader;

    @PostMapping("/{id}/imagens")
    public List<NovaImagemResponse> adicionarImagens(@PathVariable Long id, @Valid NovasImagensRequest request,
                                                     @AuthenticationPrincipal Usuario usuario) {
        Set<String> urls = uploader.enviar(request.getImagens());

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto.not-found"));

        produto.validarUsuario(usuario);
        produto.adicionarImagens(urls);
        produtoRepository.save(produto);

        return produto.getImagens()
                .stream()
                .map(ImagemProduto::toDto)
                .collect(Collectors.toList());
    }
}
