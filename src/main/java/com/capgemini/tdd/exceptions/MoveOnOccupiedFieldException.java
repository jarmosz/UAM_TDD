package com.capgemini.tdd.exceptions;

public class MoveOnOccupiedFieldException extends RuntimeException{
    public MoveOnOccupiedFieldException(String errMessage){
        super(errMessage);
    }
}
