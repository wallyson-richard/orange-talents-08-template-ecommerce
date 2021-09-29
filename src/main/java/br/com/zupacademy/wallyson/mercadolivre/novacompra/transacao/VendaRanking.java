package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.adicionarranking.NovaVendaRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaRanking implements EventoTransacaoAprovada {

    @Autowired
    private ApiClient apiClient;

    @Override
    public void processar(Compra compra) {
        var usuarioId = compra.getUsuario().getId();
        var compraId = compra.getId();
        var request = new NovaVendaRankingRequest(usuarioId, compraId);
        apiClient.sendRequest(request);
    }
}
