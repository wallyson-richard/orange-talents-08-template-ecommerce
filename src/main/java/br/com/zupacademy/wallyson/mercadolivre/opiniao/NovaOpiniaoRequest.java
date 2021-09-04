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

    public NovaOpiniaoRequest(Byte nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Produto produto, Usuario usuario) {
        return new Opiniao(nota, titulo, descricao, usuario, produto);
    }
}
