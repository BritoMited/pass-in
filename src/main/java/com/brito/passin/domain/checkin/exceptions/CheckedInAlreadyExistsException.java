package com.brito.passin.domain.checkin.exceptions;

public class CheckedInAlreadyExistsException extends RuntimeException{

    public CheckedInAlreadyExistsException(String msg){
        super(msg);
    }
}
