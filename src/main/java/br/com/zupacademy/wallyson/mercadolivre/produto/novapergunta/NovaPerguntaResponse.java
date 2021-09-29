package br.com.zupacademy.wallyson.mercadolivre.produto.novapergunta;

public class NovaPerguntaResponse {

    private Long id;

    private String titulo;

    private String criadoEm;

    public NovaPerguntaResponse(Long id, String titulo, String criadoEm) {
        this.id = id;
        this.titulo = titulo;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCriadoEm() {
        return criadoEm;
    }
}
