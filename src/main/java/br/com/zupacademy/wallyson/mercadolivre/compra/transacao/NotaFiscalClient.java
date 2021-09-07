package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.configuration.feign.FeignConfiguration;
import br.com.zupacademy.wallyson.mercadolivre.notafiscal.NovaNotaFiscalRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notas", configuration = FeignConfiguration.class, url = "http://localhost:8080/")
public interface NotaFiscalClient {

    @PostMapping("/notas")
    void save(NovaNotaFiscalRequest request);
}
