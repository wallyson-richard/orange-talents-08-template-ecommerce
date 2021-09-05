package br.com.zupacademy.wallyson.mercadolivre.produto.detalhe;

import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos")
public class DetalheProdutoController {

    private final ProdutoRepository produtoRepository;

    public DetalheProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/{id}")
    public DetalheProdutoResponse findById(@PathVariable Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return new DetalheProdutoResponse(produto);
    }
}
