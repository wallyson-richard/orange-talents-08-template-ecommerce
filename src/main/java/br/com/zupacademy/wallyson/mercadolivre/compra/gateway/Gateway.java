package br.com.zupacademy.wallyson.mercadolivre.compra.gateway;

public enum Gateway {
    PAGSEGURO(new GatewayPagPagamentoSeguro()),
    PAYPAL(new GatewayPagamentoPaypal());

    private GatewayPagamento gatewayPagamento;

    Gateway(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
