package com.brito.passin.config;

import com.brito.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.brito.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import com.brito.passin.domain.checkin.exceptions.CheckedInAlreadyExistsException;
import com.brito.passin.domain.event.exceptions.EventFullException;
import com.brito.passin.domain.event.exceptions.EventNotFoundException;
import com.brito.passin.dto.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErrorResponseDTO> handleEventFull(EventFullException exception){
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handleAttendeeNotFound(AttendeeNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handleAttendeeAlreadyExists(AttendeeAlreadyExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckedInAlreadyExistsException.class)
    public ResponseEntity handleCheckedInAlreadyExists(CheckedInAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


}
