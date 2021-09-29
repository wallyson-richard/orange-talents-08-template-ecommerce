package br.com.zupacademy.wallyson.mercadolivre.compartilhado.exceptions;

public class InvalidUserException extends RuntimeException {

    private String message;

    public InvalidUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
