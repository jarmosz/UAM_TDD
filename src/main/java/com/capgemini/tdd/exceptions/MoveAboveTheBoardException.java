package com.capgemini.tdd.exceptions;

public class MoveAboveTheBoardException extends RuntimeException {
    public MoveAboveTheBoardException(String errMessage){
        super(errMessage);
    }
}
