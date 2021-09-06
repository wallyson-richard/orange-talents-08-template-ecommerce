package br.com.zupacademy.wallyson.mercadolivre.sender;

import br.com.zupacademy.wallyson.mercadolivre.compra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.produto.pergunta.Pergunta;

public interface EmailSender {
    void perguntaCadastrada(Pergunta pergunta);

    void compraRealizada(Compra compra);
}
