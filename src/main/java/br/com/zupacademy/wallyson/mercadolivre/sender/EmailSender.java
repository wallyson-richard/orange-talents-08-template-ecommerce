package br.com.zupacademy.wallyson.mercadolivre.sender;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.Compra;
import br.com.zupacademy.wallyson.mercadolivre.produto.novapergunta.Pergunta;

public interface EmailSender {
    void perguntaCadastrada(Pergunta pergunta);

    void compraRealizada(Compra compra);

    void pagamentoAprovado(Compra compra);

    void pagamentoRecusado(Compra compra);
}
