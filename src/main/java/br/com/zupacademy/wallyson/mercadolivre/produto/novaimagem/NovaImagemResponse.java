package br.com.zupacademy.wallyson.mercadolivre.produto.novaimagem;

public class NovaImagemResponse {

    private Long id;

    private String url;

    public NovaImagemResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
