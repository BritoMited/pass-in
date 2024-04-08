package com.brito.passin.domain.attendee.exceptions;

public class AttendeeAlreadyExistException extends RuntimeException{

    public AttendeeAlreadyExistException(String msg){
        super(msg);
    }

}
