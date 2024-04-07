package com.brito.passin.config;

import com.brito.passin.domain.event.exceptions.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// classe de tratamento de erros
@ControllerAdvice
public class ExceptionEntityHandler {

    //aqui nessa notação a gente avisa que a gente lida com uma exceção
    // dando o retorno de codigo http adequado, por ser um ControllerAdvice
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
