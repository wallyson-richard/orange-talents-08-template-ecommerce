package br.com.zupacademy.wallyson.mercadolivre.novacompra.gateway;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;

public interface GatewayPagamento {

    String gerarPagamento(Compra compra);
}
