package br.com.zupacademy.wallyson.mercadolivre.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorResponse> erros = new ArrayList<>();
        ex.getFieldErrors().forEach(field -> erros.add(new ErrorResponse(field.getField(), field.getDefaultMessage())));
        return erros;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<List<MessageErrorResponse>> responseStatusException(ResponseStatusException ex) {
        var messageError = getMessage(ex.getReason());
        return ResponseEntity.status(ex.getStatus())
                .body(List.of(new MessageErrorResponse(messageError)));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public MessageErrorResponse exception(Exception ex) {
        return new MessageErrorResponse("Houve um erro ao tentar realizar a operação. Por favor entre em contato" +
                "com o suporte");
    }

    private String getMessage(String key) {
        return key != null
                ? messageSource.getMessage(key, null, LocaleContextHolder.getLocale())
                : key;
    }
}
