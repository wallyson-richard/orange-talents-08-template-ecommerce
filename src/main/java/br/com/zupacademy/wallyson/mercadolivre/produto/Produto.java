package br.com.zupacademy.wallyson.mercadolivre.produto;

import br.com.zupacademy.wallyson.mercadolivre.categoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Long quantidade;

    @NotNull
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @Size(min = 3)
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<Caracteristica> caracteristicas;

    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero Long quantidade,
                   @NotNull @Size(max = 1000) String descricao, @NotNull Categoria categoria,
                   @NotNull @Size(min = 3) @Valid Set<Caracteristica> caracteristicas, @NotNull Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.usuario = usuario;
    }
}
