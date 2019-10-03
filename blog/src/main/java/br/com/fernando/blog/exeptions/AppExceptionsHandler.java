package br.com.fernando.blog.exeptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handler(Exception ex, WebRequest request){
        final ErroMessage build = ErroMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(build, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> mine(Exception ex, WebRequest request){
        final ErroMessage build = ErroMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .message("Null pointer classe")
                .build();
        return new ResponseEntity<>(build, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalAccessError.class,
    NumberFormatException.class})
    public ResponseEntity<Object> many(Exception ex, WebRequest request){
        final ErroMessage build = ErroMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .message("Null pointer classe")
                .build();
        return new ResponseEntity<>(build, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
