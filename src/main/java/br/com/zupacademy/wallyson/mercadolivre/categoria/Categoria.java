package br.com.zupacademy.wallyson.mercadolivre.categoria;

import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank @Unique(entity = Categoria.class, attribute = "nome") String nome) {
        this.nome = nome;
    }

    public Categoria(@NotBlank @Unique(entity = Categoria.class, attribute = "nome") String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
}
