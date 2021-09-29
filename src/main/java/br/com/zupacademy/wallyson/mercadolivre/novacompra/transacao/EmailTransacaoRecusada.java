package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailTransacaoRecusada implements EventoTransacaoRecusada {

    private EmailSender emailSender;

    @Override
    public void processar(Compra compra) {
        emailSender.pagamentoRecusado(compra);
    }
}
