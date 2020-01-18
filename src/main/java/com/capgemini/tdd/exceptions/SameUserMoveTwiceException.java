package com.capgemini.tdd.exceptions;

public class SameUserMoveTwiceException extends RuntimeException {
    public SameUserMoveTwiceException(String errorMesasage){
        super(errorMesasage);
    }
}
