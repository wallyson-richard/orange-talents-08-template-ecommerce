package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;

public interface RetornoTransacaoRequest {

    Transacao toModel(Compra compra);
}
