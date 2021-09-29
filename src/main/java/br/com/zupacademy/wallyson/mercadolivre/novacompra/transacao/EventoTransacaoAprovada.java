package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;

public interface EventoTransacaoAprovada {

    void processar(Compra compra);
}
