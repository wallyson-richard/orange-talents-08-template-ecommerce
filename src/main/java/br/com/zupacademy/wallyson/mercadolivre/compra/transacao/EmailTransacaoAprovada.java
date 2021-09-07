package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailTransacaoAprovada implements EventoTransacaoAprovada {

    private final EmailSender emailSender;

    public EmailTransacaoAprovada(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void processar(Compra compra) {
        this.emailSender.compraRealizada(compra);
    }
}
