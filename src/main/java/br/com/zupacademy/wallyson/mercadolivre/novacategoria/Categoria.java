package br.com.zupacademy.wallyson.mercadolivre.novacategoria;

import br.com.zupacademy.wallyson.mercadolivre.compartilhado.AtributoUnico;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank @Unique(AtributoUnico.CATEGORIA_NOME) String nome) {
        this.nome = nome;
    }

    public Categoria(@NotBlank @Unique(AtributoUnico.CATEGORIA_NOME) String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public NovaCategoriaResponse toDto() {
        return categoria != null
                ? new NovaCategoriaResponse(id, nome, categoria.toDto())
                : new NovaCategoriaResponse(id, nome);
    }
}
