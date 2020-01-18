package com.capgemini.tdd.exceptions;

public class UserPlayWithHimselfException extends RuntimeException {
    public UserPlayWithHimselfException(String errMessage){
        super(errMessage);
    }
}
