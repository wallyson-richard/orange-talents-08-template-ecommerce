package br.com.zupacademy.wallyson.mercadolivre.ranking;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @PostMapping
    public void save(@RequestBody @Valid NovaVendaRankingRequest request) {
        System.out.println(request);
    }
}
