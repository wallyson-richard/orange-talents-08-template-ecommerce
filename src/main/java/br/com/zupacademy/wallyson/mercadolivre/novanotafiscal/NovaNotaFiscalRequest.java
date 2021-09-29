package br.com.zupacademy.wallyson.mercadolivre.novanotafiscal;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;

import javax.validation.constraints.NotNull;

public class NovaNotaFiscalRequest {

    @NotNull
    @Exist(entity = Usuario.class)
    private Long usuarioId;

    @NotNull
    @Exist(entity = Compra.class)
    private Long compraId;

    public NovaNotaFiscalRequest(Long usuarioId, Long compraId) {
        this.usuarioId = usuarioId;
        this.compraId = compraId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getCompraId() {
        return compraId;
    }
}
