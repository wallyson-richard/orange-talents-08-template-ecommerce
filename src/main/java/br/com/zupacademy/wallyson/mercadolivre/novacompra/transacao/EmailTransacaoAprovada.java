package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailTransacaoAprovada implements EventoTransacaoAprovada {

    @Autowired
    private EmailSender emailSender;

    @Override
    public void processar(Compra compra) {
        this.emailSender.compraRealizada(compra);
    }
}
