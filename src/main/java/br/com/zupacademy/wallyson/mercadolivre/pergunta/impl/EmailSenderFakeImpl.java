package br.com.zupacademy.wallyson.mercadolivre.pergunta.impl;

import br.com.zupacademy.wallyson.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.wallyson.mercadolivre.pergunta.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderFakeImpl implements EmailSender {

    public void enviar(Pergunta pergunta) {
        System.out.printf("Email enviado: %s\n", pergunta);
    }
}
