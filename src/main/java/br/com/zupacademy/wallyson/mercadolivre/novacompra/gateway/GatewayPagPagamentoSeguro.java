package br.com.zupacademy.wallyson.mercadolivre.novacompra.gateway;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GatewayPagPagamentoSeguro implements GatewayPagamento {
    @Override
    public String gerarPagamento(Compra compra) {
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/pagseguro/{id}")
                .buildAndExpand(compra.getId())
                .toUriString();
        return String.format("pagseguro.com?returnId=%d&redirectUrl=%s", compra.getId(), uri);
    }
}
