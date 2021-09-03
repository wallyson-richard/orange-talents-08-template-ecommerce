package br.com.zupacademy.wallyson.mercadolivre.produto;

import br.com.zupacademy.wallyson.mercadolivre.categoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal valor;

    private Long quantidade;

    private String descricao;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<Caracteristica> caracteristicas;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Usuario usuario;

    public Produto(String nome, BigDecimal valor, Long quantidade, String descricao, Categoria categoria, Set<Caracteristica> caracteristicas, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.usuario = usuario;
    }
}
