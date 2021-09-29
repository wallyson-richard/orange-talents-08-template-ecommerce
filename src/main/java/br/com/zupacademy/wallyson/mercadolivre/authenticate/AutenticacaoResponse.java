package br.com.zupacademy.wallyson.mercadolivre.authenticate;

public class AutenticacaoResponse {

    private String token;
    private String tipo;

    public AutenticacaoResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
