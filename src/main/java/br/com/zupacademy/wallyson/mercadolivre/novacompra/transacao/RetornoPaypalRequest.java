package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compartilhado.AtributoUnico;
import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPaypalRequest implements RetornoTransacaoRequest {

    @NotBlank
    @Unique(AtributoUnico.TRANSACAO_ID)
    private String transacaoId;

    @Min(0)
    @Max(1)
    @NotNull
    private Byte statusTransacao;

    public RetornoPaypalRequest(Long compraid, String transacaoId, Byte statusTransacao) {
        this.transacaoId = transacaoId;
        this.statusTransacao = statusTransacao;
    }

    public Transacao toModel(Compra compra) {
        var status = StatusTransacao.getStatusTransacao(statusTransacao);
        return new Transacao(status, transacaoId, compra);
    }
}
