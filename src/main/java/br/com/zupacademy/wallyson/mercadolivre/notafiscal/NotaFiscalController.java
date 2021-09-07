package br.com.zupacademy.wallyson.mercadolivre.notafiscal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/notas")
public class NotaFiscalController {

    @PostMapping
    public void save(@RequestBody @Valid NovaNotaFiscalRequest request) {
        System.out.println(request);
    }
}
