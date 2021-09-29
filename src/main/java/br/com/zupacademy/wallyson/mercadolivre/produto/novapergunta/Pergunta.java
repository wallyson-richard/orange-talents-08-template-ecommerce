package br.com.zupacademy.wallyson.mercadolivre.produto.novapergunta;

import br.com.zupacademy.wallyson.mercadolivre.compartilhado.DateUtils;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public NovaPerguntaResponse toDto() {
        return new NovaPerguntaResponse(id, titulo, DateUtils.dataFormatada(createdAt));
    }
}
