package com.capgemini.tdd.dao.entities;

public class BoardBE
{
    private Long id;
    private UserBE playerOne;
    private UserBE playerTwo;


    public BoardBE(final UserBE pPlayerOne, final UserBE pPlayerTwo)
    {
        playerOne = pPlayerOne;
        playerTwo = pPlayerTwo;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long pId)
    {
        id = pId;
    }

    public UserBE getPlayerOne()
    {
        return playerOne;
    }

    public UserBE getPlayerTwo()
    {
        return playerTwo;
    }

    @Override
    public String toString()
    {
        return "BoardBE{" +
               "id=" + id +
               ", playerOne=" + playerOne +
               ", playerTwo=" + playerTwo +
               '}';
    }

    @Override
    public boolean equals(final Object pO)
    {
        if (this == pO)
            return true;
        if (pO == null || getClass() != pO.getClass())
            return false;

        final BoardBE boardBE = (BoardBE) pO;

        if (id != null ? !id.equals(boardBE.id) : boardBE.id != null)
            return false;
        if (playerOne != null ? !playerOne.equals(boardBE.playerOne) : boardBE.playerOne != null)
            return false;
        return playerTwo != null ? playerTwo.equals(boardBE.playerTwo) : boardBE.playerTwo == null;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playerOne != null ? playerOne.hashCode() : 0);
        result = 31 * result + (playerTwo != null ? playerTwo.hashCode() : 0);
        return result;
    }
}
