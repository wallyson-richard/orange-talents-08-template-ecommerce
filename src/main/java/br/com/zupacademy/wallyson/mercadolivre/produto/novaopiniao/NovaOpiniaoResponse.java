package br.com.zupacademy.wallyson.mercadolivre.produto.novaopiniao;

public class NovaOpiniaoResponse {

    private Long id;

    private Byte nota;

    private String titulo;

    private String descricao;

    public NovaOpiniaoResponse(Long id, Byte nota, String titulo, String descricao) {
        this.id = id;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
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
