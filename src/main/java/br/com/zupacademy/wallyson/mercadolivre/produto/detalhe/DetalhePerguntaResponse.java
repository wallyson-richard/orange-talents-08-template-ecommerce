package br.com.zupacademy.wallyson.mercadolivre.produto.detalhe;

import br.com.zupacademy.wallyson.mercadolivre.produto.pergunta.Pergunta;

public class DetalhePerguntaResponse {

    private String descricao;

    public DetalhePerguntaResponse(Pergunta pergunta) {
        this.descricao = pergunta.getTitulo();
    }

    public String getDescricao() {
        return descricao;
    }
}
