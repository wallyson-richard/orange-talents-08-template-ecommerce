package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.ranking.NovaVendaRankingRequest;
import org.springframework.stereotype.Service;

@Service
public class VendaRanking implements EventoTransacaoAprovada {

    private final RankingClient rankingClient;

    public VendaRanking(RankingClient rankingClient) {
        this.rankingClient = rankingClient;
    }

    @Override
    public void processar(Compra compra) {
        var usuarioId = compra.getUsuario().getId();
        var compraId = compra.getId();
        var request = new NovaVendaRankingRequest(usuarioId, compraId);
        rankingClient.save(request);
    }
}
