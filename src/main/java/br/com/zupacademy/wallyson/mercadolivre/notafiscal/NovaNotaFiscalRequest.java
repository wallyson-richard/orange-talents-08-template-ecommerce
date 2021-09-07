package br.com.zupacademy.wallyson.mercadolivre.notafiscal;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
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
