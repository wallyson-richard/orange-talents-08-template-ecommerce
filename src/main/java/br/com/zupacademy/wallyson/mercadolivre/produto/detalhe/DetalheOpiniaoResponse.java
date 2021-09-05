package br.com.zupacademy.wallyson.mercadolivre.produto.detalhe;

import br.com.zupacademy.wallyson.mercadolivre.produto.opiniao.Opiniao;

public class DetalheOpiniaoResponse {

    private Byte nota;

    private String titulo;

    private String descricao;

    public DetalheOpiniaoResponse(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Byte getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
