package br.com.zupacademy.wallyson.mercadolivre.compra.gateway;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;

public interface GatewayPagamento {

    String gerarPagamento(Compra compra);
}
