package com.capgemini.tdd.services.requestparams;

public class NewMoveRequestParams {
    private String userName;
    private NewGameRequestParams newGame;
    private Long xCoordinate;
    private Long yCoordinate;
    private String value;

    public NewMoveRequestParams(String userName, NewGameRequestParams newGame, Long xCoordinate, Long yCoordinate, String value) {
        this.userName = userName;
        this.newGame = newGame;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.value = value;
    }

    public String getUserName() {
        return userName;
    }

    public NewGameRequestParams getNewGame() {
        return newGame;
    }

    public Long getxCoordinate() {
        return xCoordinate;
    }

    public Long getyCoordinate() {
        return yCoordinate;
    }

    public String getValue() {
        return value;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNewGame(NewGameRequestParams newGame) {
        this.newGame = newGame;
    }

    public void setxCoordinate(Long xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(Long yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
