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

    @Override
    public void pagamentoAprovado(Compra compra) {
        System.out.printf("Pagamento aprovado com sucesso: %s\n", compra);
    }

    @Override
    public void pagamentoRecusado(Compra compra) {
        System.out.printf("Pagamento recusado: %s\n", compra);
    }
}
