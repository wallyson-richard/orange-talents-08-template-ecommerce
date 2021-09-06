package br.com.zupacademy.wallyson.mercadolivre.sender.impl;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderFakeImpl implements EmailSender {

    public void perguntaCadastrada(Pergunta pergunta) {
        System.out.printf("Email enviado: %s\n", pergunta);
    }

    @Override
    public void compraRealizada(Compra compra) {
        System.out.printf("Compra realizada com sucesso:  %s\n", compra);
    }
}
