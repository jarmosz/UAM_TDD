package com.capgemini.tdd.exceptions;

public class UserSetNotHisObjectException extends RuntimeException {
    public UserSetNotHisObjectException(String errorMessage){
        super(errorMessage);
    }
}
