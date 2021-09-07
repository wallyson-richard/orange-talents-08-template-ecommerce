package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.compra.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosRetornoTransacao {

    private final Set<EventoTransacaoAprovada> eventosAprovados;
    private final Set<EventoTransacaoRecusada> eventoRecusados;
    private final CompraRepository compraRepository;

    public EventosRetornoTransacao(Set<EventoTransacaoAprovada> eventosAprovados,
                                   Set<EventoTransacaoRecusada> eventoRecusados,
                                   CompraRepository compraRepository) {
        this.eventosAprovados = eventosAprovados;
        this.eventoRecusados = eventoRecusados;
        this.compraRepository = compraRepository;
    }

    public void processar(Compra compra) {
        if (compra.aprovada()) {
            eventosAprovados.forEach(evento -> evento.processar(compra));
            compraRepository.save(compra);
        } else {
            eventoRecusados.forEach(evento -> evento.processar(compra));
        }
    }
}
