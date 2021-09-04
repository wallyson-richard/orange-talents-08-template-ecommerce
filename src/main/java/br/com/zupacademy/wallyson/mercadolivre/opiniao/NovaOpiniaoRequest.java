package br.com.zupacademy.wallyson.mercadolivre.opiniao;

import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;

import javax.validation.constraints.*;

public class NovaOpiniaoRequest {

    @Min(1)
    @Max(5)
    private Byte nota;

    @NotBlank
    private String titulo;

    @Size(max = 500)
    @NotBlank
    private String descricao;

    @NotNull
    @Exist(entity = Produto.class)
    private Long produtoId;

    public NovaOpiniaoRequest(Byte nota, String titulo, String descricao, Long produtoId) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoId = produtoId;
    }

    public Opiniao toModel(ProdutoRepository produtoRepository, Usuario usuario) {
        return produtoRepository.findById(produtoId).map(produto -> {
            return new Opiniao(nota, titulo, descricao, usuario, produto);
        }).orElseThrow(() -> new IllegalArgumentException("Produto informado não existe."));
    }
}
