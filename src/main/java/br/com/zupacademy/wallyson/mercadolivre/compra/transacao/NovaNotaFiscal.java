package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.notafiscal.NovaNotaFiscalRequest;
import org.springframework.stereotype.Service;

@Service
public class NovaNotaFiscal implements EventoTransacaoAprovada {

    private final NotaFiscalClient notaFiscalClient;

    public NovaNotaFiscal(NotaFiscalClient notaFiscalClient) {
        this.notaFiscalClient = notaFiscalClient;
    }

    @Override
    public void processar(Compra compra) {
        var usuarioId = compra.getUsuario().getId();
        var compraId = compra.getId();
        var request = new NovaNotaFiscalRequest(usuarioId, compraId);
        notaFiscalClient.save(request);
    }
}
