package com.capgemini.tdd.services.game;

import com.capgemini.tdd.core.data.BoardStatusEnum;
import com.capgemini.tdd.dao.entities.BoardBE;
import com.capgemini.tdd.dao.entities.MatchResultBE;
import com.capgemini.tdd.exceptions.*;
import com.capgemini.tdd.services.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceIntegrationTest
{

    @Autowired
    private GameService gameService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchResultService matchResultService;

    @Test
    public void shouldMakeMovesToFinishGame()
    {
        //given
        BoardBE boardBE = new BoardBE(userService.findByName("Adam"), userService.findByName("Kasztan"));
        boardBE = boardService.save(boardBE);

        //when
        long boardId = boardBE.getId();
        gameService.makeMove(boardId, 0L, 0L, "Adam", "X");
        gameService.makeMove(boardId, 2L, 0L, "Kasztan", "O");

        gameService.makeMove(boardId, 0L, 1L, "Adam", "X");
        BoardStatusEnum tempBoardStatusEnum = gameService.makeMove(boardId, 2L, 1L, "Kasztan", "O");

        BoardStatusEnum finalBoardStatusEnum = gameService.makeMove(boardId, 0L, 2L, "Adam", "X");

        //then
        Assert.assertEquals(BoardStatusEnum.IN_PROGRESS, tempBoardStatusEnum);
        Assert.assertEquals(BoardStatusEnum.GAME_OVER, finalBoardStatusEnum);
        MatchResultBE matchResult = matchResultService.findByBoardId(boardId);
        Assert.assertNotNull(matchResult);
        Assert.assertEquals("Adam", matchResult.getWinner().getName());

    }

    @Test(expected = SameUserMoveTwiceException.class)
    public void shouldSameUserMakeMove(){
        // given
        BoardBE boardBE = new BoardBE(userService.findByName("Adam"), userService.findByName("Kasztan"));
        boardBE = boardService.save(boardBE);
        long boardId = boardBE.getId();

        // when
        gameService.makeMove(boardId, 0L, 0L, "Kasztan", "X");
        gameService.makeMove(boardId, 1L, 2L, "Kasztan", "O");
    }

    @Test(expected = UserSetNotHisObjectException.class)
    public void shouldPlayerSetNotHisObject(){
        //given
        BoardBE boardBE = new BoardBE(userService.findByName("Adam"), userService.findByName("Kasztan"));
        boardBE = boardService.save(boardBE);
        long boardId = boardBE.getId();


        //when
        gameService.makeMove(boardId, 0L, 0L, "Kasztan", "X");
        gameService.makeMove(boardId, 1L, 2L, "Adam", "O");
        gameService.makeMove(boardId, 3L, 1L, "Kasztan", "X");
        gameService.makeMove(boardId, 3L, 1L, "Adam", "O");
    }

    @Test
    public void shouldPlayerMakeMoveWhenGameOver(){
        //given
        BoardBE boardBE = new BoardBE(userService.findByName("Adam"), userService.findByName("Kasztan"));
        boardBE = boardService.save(boardBE);
        long boardId = boardBE.getId();


        //when
        gameService.makeMove(boardId, 0L, 0L, "Adam", "X");
        gameService.makeMove(boardId, 2L, 0L, "Kasztan", "O");
        gameService.makeMove(boardId, 0L, 1L, "Adam", "X");
        gameService.makeMove(boardId, 2L, 1L, "Kasztan", "O");
        gameService.makeMove(boardId, 0L, 2L, "Adam", "X");
        BoardStatusEnum finalStatus = gameService.makeMove(boardId, 3L, 1L, "Kasztan", "O");

        //then
        Assert.assertEquals(finalStatus, BoardStatusEnum.GAME_OVER);

    }

    @Test(expected = MoveOnOccupiedFieldException.class)
    public void shouldPlayerMakeMoveOnOccupiedField(){
        //given
        BoardBE boardBE = new BoardBE(userService.findByName("Adam"), userService.findByName("Kasztan"));
        boardBE = boardService.save(boardBE);
        long boardId = boardBE.getId();


        //when
        gameService.makeMove(boardId, 0L, 0L, "Adam", "X");
        gameService.makeMove(boardId, 2L, 0L, "Kasztan", "O");
        gameService.makeMove(boardId, 0L, 1L, "Adam", "X");
        gameService.makeMove(boardId, 2L, 1L, "Kasztan", "O");
        gameService.makeMove(boardId, 2L, 0L, "Adam", "X");

    }

    @Test(expected = MoveAboveTheBoardException.class)
    public void shouldPlayerMoveAboveTheBoard(){
        //given
        BoardBE boardBE = new BoardBE(userService.findByName("Adam"), userService.findByName("Kasztan"));
        boardBE = boardService.save(boardBE);
        long boardId = boardBE.getId();

        //when
        gameService.makeMove(boardId, 0L, 0L, "Adam", "X");
        gameService.makeMove(boardId, 2L, 0L, "Kasztan", "O");
        gameService.makeMove(boardId, 55L, 43L, "Adam", "X");

    }


    @Test(expected = UserPlayWithHimselfException.class)
    public void shouldUserPlayWithHimself(){
        //given
        BoardBE boardBE = new BoardBE(userService.findByName("Adam"), userService.findByName("Adam"));
        boardBE = boardService.save(boardBE);
        long boardId = boardBE.getId();


        //when
        gameService.makeMove(boardId, 0L, 0L, "Adam", "X");
        gameService.makeMove(boardId, 1L, 1L, "Adam", "X");

    }


}
