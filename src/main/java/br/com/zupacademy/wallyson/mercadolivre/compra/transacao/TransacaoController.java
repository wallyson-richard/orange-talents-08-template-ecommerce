package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.CompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class TransacaoController {

    private CompraRepository compraRepository;
    private EventosRetornoTransacao eventosRetornoTransacao;

    public TransacaoController(CompraRepository compraRepository, EventosRetornoTransacao eventosRetornoTransacao) {
        this.compraRepository = compraRepository;
        this.eventosRetornoTransacao = eventosRetornoTransacao;
    }

    @PostMapping("/pagseguro/{id}")
    public void salvarTransacaoPagseguro(@PathVariable("id") Long compraId, @RequestBody @Valid RetornoPagseguroRequest request) {
        processaTransacao(compraId, request);
    }

    @PostMapping("/paypal/{id}")
    public void salvarTransacaoPagseguro(@PathVariable("id") Long compraId, @RequestBody @Valid RetornoPaypalRequest request) {
        processaTransacao(compraId, request);

    }

    private void processaTransacao(Long compraId, RetornoTransacaoRequest request) {
        var compra = compraRepository.findById(compraId).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A compra informada não existe!");
        });
        var transacao = request.toModel(compra);
        compra.adicionaTransacao(transacao);
        eventosRetornoTransacao.processar(compra);
    }
}
