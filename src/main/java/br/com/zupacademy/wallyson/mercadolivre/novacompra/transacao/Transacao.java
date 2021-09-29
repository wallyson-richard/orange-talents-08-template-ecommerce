package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String transacaoId;

    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String transacaoId, @NotNull @Valid Compra compra) {
        this.status = status;
        this.transacaoId = transacaoId;
        this.compra = compra;
    }

    public boolean aprovada() {
        return this.status.equals(StatusTransacao.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(transacaoId, transacao.transacaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transacaoId);
    }
}
