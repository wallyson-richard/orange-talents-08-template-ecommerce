package br.com.zupacademy.wallyson.mercadolivre.compra;

import br.com.zupacademy.wallyson.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Long quantidade;

    @ManyToOne
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADA;

    @Deprecated
    public Compra() {
    }

    public Compra(Long quantidade, Produto produto, Gateway gateway, Usuario usuario) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.gateway = gateway;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Gateway getGateway() {
        return gateway;
    }
}
