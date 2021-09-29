package br.com.zupacademy.wallyson.mercadolivre.produto.consultaproduto;

import br.com.zupacademy.wallyson.mercadolivre.produto.novacaracteristica.Caracteristica;

public class DetalheCaracteristicasResponse {

    private String nome;

    private String descricao;

    public DetalheCaracteristicasResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
