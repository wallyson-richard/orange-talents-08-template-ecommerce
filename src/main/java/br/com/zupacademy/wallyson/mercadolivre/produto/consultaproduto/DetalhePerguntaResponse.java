package br.com.zupacademy.wallyson.mercadolivre.produto.consultaproduto;

import br.com.zupacademy.wallyson.mercadolivre.produto.novapergunta.Pergunta;

public class DetalhePerguntaResponse {

    private String descricao;

    public DetalhePerguntaResponse(Pergunta pergunta) {
        this.descricao = pergunta.getTitulo();
    }

    public String getDescricao() {
        return descricao;
    }
}
