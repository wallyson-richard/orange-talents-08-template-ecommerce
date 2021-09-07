package br.com.zupacademy.wallyson.mercadolivre.compra;

import br.com.zupacademy.wallyson.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.wallyson.mercadolivre.compra.transacao.Transacao;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void adicionaTransacao(Transacao transacao) {
        var existeMaisDeDuasTransacoesAprovados = transacoesAprovadas().size() > 2;
        var transacaoAprovada = transacao.aprovada();
        if (existeMaisDeDuasTransacoesAprovados && transacaoAprovada) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Uma compra não pode possuir mais de duas transações aprovadas.");
        }
        this.transacoes.add(transacao);
    }

    public boolean aprovada() {
        return !transacoesAprovadas().isEmpty();
    }

    public Set<Transacao> transacoesAprovadas() {
        return this.transacoes.stream()
                .filter(Transacao::aprovada)
                .collect(Collectors.toSet());
    }

}
