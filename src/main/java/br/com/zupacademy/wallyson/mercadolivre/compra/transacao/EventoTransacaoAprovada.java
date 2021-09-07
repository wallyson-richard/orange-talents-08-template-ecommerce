package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;

public interface EventoTransacaoAprovada {

    void processar(Compra compra);
}
