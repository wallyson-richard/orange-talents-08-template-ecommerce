package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;

import javax.validation.constraints.NotBlank;

public class RetornoPagseguroRequest implements RetornoTransacaoRequest {

    @NotBlank
    @Unique(entity = Transacao.class, attribute = "transacaoId")
    private String transacaoId;

    @NotBlank
    private String statusTransacao;

    public RetornoPagseguroRequest(String transacaoId, String statusTransacao) {
        this.transacaoId = transacaoId;
        this.statusTransacao = statusTransacao;
    }

    public Transacao toModel(Compra compra) {
        var status = StatusTransacao.valueOf(statusTransacao.toUpperCase());
        return new Transacao(status, transacaoId, compra);
    }
}
