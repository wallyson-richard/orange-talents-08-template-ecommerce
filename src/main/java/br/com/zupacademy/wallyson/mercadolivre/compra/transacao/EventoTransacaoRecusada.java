package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;

public interface EventoTransacaoRecusada {
    void processar(Compra compra);
}
