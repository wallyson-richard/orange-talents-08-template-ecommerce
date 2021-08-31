package br.com.zupacademy.wallyson.mercadolivre.exceptionhandler;

public class ErrorResponse {

    private String campo;
    private final String mensagem;

    public ErrorResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
