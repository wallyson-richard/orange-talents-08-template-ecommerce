package br.com.zupacademy.wallyson.mercadolivre.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorResponse> erros = new ArrayList<>();
        ex.getFieldErrors().forEach(field -> erros.add(new ErrorResponse(field.getField(), field.getDefaultMessage())));
        return ResponseEntity.badRequest().body(erros);
    }
}
