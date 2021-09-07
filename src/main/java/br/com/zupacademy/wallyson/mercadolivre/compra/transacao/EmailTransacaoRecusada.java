package br.com.zupacademy.wallyson.mercadolivre.compra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;

public class EmailTransacaoRecusada implements EventoTransacaoRecusada {

    private EmailSender emailSender;

    public EmailTransacaoRecusada(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void processar(Compra compra) {
        emailSender.pagamentoRecusado(compra);
    }
}
