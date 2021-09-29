package br.com.zupacademy.wallyson.mercadolivre.novacategoria;

public class NovaCategoriaResponse {

    private Long id;

    private String nome;

    private NovaCategoriaResponse categoria;

    public NovaCategoriaResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public NovaCategoriaResponse(Long id, String nome, NovaCategoriaResponse categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public NovaCategoriaResponse getCategoria() {
        return categoria;
    }
}
