package br.com.zupacademy.wallyson.mercadolivre.novacompra.gateway;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GatewayPagamentoPaypal implements GatewayPagamento {
    @Override
    public String gerarPagamento(Compra compra) {
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/paypal/{id}")
                .buildAndExpand(compra.getId())
                .toUriString();
        return String.format("paypal.com?buyerId=%d&redirectUrl=%s", compra.getId(), uri);

    }
}
