package br.com.zupacademy.wallyson.mercadolivre.compartilhado.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private String campo;
    private String message;
    private String value;

    public EntityNotFoundException(String campo, String message, Long value) {
        this.campo = campo;
        this.message = message;
        this.value = value.toString();

    }

    public String getCampo() {
        return campo;
    }

    public String getMessage() {
        return message;
    }

    public String getValue() {
        return value;
    }
}
