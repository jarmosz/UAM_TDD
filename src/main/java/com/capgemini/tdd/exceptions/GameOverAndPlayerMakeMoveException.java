package com.capgemini.tdd.exceptions;

public class GameOverAndPlayerMakeMoveException extends RuntimeException {
    public GameOverAndPlayerMakeMoveException(String errMessage){
        super(errMessage);
    }
}
