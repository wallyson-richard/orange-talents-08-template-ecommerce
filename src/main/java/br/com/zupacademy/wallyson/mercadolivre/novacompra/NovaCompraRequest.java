package br.com.zupacademy.wallyson.mercadolivre.novacompra;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.gateway.Gateway;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @NotNull
    @Positive
    private Long quantidade;

    @NotNull
    @Exist(entity = Produto.class)
    private Long productId;

    @NotNull
    private Gateway gateway;

    public NovaCompraRequest(@NotNull @Positive Long quantidade, @NotNull
    @Exist(entity = Produto.class) Long productId, @NotNull Gateway gateway) {
        this.quantidade = quantidade;
        this.productId = productId;
        this.gateway = gateway;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public Long getProductId() {
        return productId;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        return new Compra(quantidade, produto, gateway, usuario);
    }


}
