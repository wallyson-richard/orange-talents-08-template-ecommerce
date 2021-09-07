package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.configuration.feign.FeignConfiguration;
import br.com.zupacademy.wallyson.mercadolivre.ranking.NovaVendaRankingRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ranking", configuration = FeignConfiguration.class, url = "http://localhost:8080/")
public interface RankingClient {

    @PostMapping("/ranking")
    void save(NovaVendaRankingRequest request);
}
