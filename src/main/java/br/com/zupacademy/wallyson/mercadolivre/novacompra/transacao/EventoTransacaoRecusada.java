package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;

public interface EventoTransacaoRecusada {

    void processar(Compra compra);
}
