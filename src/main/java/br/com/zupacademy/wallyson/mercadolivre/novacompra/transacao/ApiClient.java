package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.adicionarranking.NovaVendaRankingRequest;
import br.com.zupacademy.wallyson.mercadolivre.novanotafiscal.NovaNotaFiscalRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "api", url = "${api.url}")
public interface ApiClient {

    @PostMapping("/notas")
    void sendRequest(NovaNotaFiscalRequest request);

    @PostMapping("/ranking")
    void sendRequest(NovaVendaRankingRequest request);

}
