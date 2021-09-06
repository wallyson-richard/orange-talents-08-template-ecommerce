package br.com.zupacademy.wallyson.mercadolivre.exceptionhandler;

public class MessageErrorResponse {

    private String messageError;

    public MessageErrorResponse(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }
}
