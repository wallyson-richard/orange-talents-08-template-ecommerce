package br.com.zupacademy.wallyson.mercadolivre.produto.opiniao;

import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private Byte nota;

    @NotBlank
    private String titulo;

    @Size(max = 500)
    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) Byte nota, @NotBlank String titulo, @Size(max = 500) @NotBlank String descricao,
                   @NotNull Usuario usuario, @NotNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }
}
