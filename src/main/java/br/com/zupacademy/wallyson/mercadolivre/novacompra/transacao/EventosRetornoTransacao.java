package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.novacompra.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosRetornoTransacao {

    @Autowired
    private Set<EventoTransacaoAprovada> eventosAprovados;

    @Autowired
    private Set<EventoTransacaoRecusada> eventoRecusados;

    @Autowired
    private CompraRepository compraRepository;

    public void processar(Compra compra) {
        if (compra.aprovada()) {
            eventosAprovados.forEach(evento -> evento.processar(compra));
            compraRepository.save(compra);
        } else {
            eventoRecusados.forEach(evento -> evento.processar(compra));
        }
    }
}
