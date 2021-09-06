package br.com.zupacademy.wallyson.mercadolivre.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorResponse> erros = new ArrayList<>();
        ex.getFieldErrors().forEach(field -> erros.add(new ErrorResponse(field.getField(), field.getDefaultMessage())));
        return erros;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    public List<MessageErrorResponse> exception(ResponseStatusException ex) {
        return List.of(new MessageErrorResponse(ex.getReason()));
    }
}
