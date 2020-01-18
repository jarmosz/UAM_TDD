package com.capgemini.tdd.core.service.impl;

import com.capgemini.tdd.core.data.Board;
import com.capgemini.tdd.core.data.Point;
import com.capgemini.tdd.core.enums.WinEnum;
import com.capgemini.tdd.dao.enums.MoveValueEnum;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardInspectorImpl
{

    private static final int BOARD_SIZE = 3;
    private static final int DIAGONAL_SUM = 4;

    public static int getBoardSize(){
        return BOARD_SIZE;
    }

    public static int getDiagonalSum(){
        return DIAGONAL_SUM;
    }

    public static WinEnum validate(@Nonnull final Board board, @Nonnull final MoveValueEnum moveValue)
    {

        Preconditions.checkNotNull(board);
        Preconditions.checkNotNull(moveValue);

        Set<Map.Entry<Point, MoveValueEnum>>
                allMovesForGivenValue = board.entrySet().stream().filter(x -> x.getValue() == moveValue).collect(Collectors.toSet());

        WinEnum horizontalStatus = lookForWinnerHorizontal(allMovesForGivenValue);
        if (WinEnum.NONE != horizontalStatus)
        {
            return horizontalStatus;
        }

        WinEnum verticalStatus = lookForWinnerVertical(allMovesForGivenValue);
        if (WinEnum.NONE != verticalStatus)
        {
            return verticalStatus;
        }
        WinEnum diagonalStatus = lookForWinnerDiagonal(allMovesForGivenValue);
        if (WinEnum.NONE != diagonalStatus)
        {
            return diagonalStatus;
        }
        return WinEnum.NONE;
    }

    private static WinEnum lookForWinnerDiagonal(final Set<Map.Entry<Point, MoveValueEnum>> allMovesForGivenValue)
    {
        List<MoveValueEnum> diagonalMovesCaseOne = allMovesForGivenValue.stream().filter(x -> x.getKey().getX().equals(x.getKey().getY()))
                                                                        .map(x -> x.getValue())
                                                                        .collect(Collectors.toList());
        List<MoveValueEnum>
                diagonalMovesCaseTwo =
                allMovesForGivenValue.stream().filter(x -> Long.valueOf(x.getKey().getX() + x.getKey().getY()).equals(Long.valueOf(
                        DIAGONAL_SUM)))
                                     .map(x -> x.getValue())
                                     .collect(Collectors.toList());
        return (diagonalMovesCaseOne.size() == BOARD_SIZE || diagonalMovesCaseTwo.size() == BOARD_SIZE) ? WinEnum.DONE : WinEnum.NONE;
    }

    private static WinEnum lookForWinnerVertical(final Set<Map.Entry<Point, MoveValueEnum>> allMovesForGivenValue)
    {

        List<List<Map.Entry<Point, MoveValueEnum>>>
                movesGroupedByColumn =
                allMovesForGivenValue.stream()
                                     .collect(Collectors.groupingBy(x -> x.getKey().getY()))
                                     .entrySet()
                                     .stream()
                                     .map(x -> x.getValue())
                                     .collect(Collectors.toList());

        for (List<Map.Entry<Point, MoveValueEnum>> entryList : movesGroupedByColumn)
        {
            if (lookForWinnerInCollection(entryList))
            {
                return WinEnum.DONE;
            }
        }
        return WinEnum.NONE;
    }

    private static WinEnum lookForWinnerHorizontal(final Set<Map.Entry<Point, MoveValueEnum>> allMovesForGivenValue)
    {
        List<List<Map.Entry<Point, MoveValueEnum>>>
                movesGroupedByRow =
                allMovesForGivenValue.stream()
                                     .collect(Collectors.groupingBy(x -> x.getKey().getX()))
                                     .entrySet()
                                     .stream()
                                     .map(x -> x.getValue())
                                     .collect(Collectors.toList());

        for (List<Map.Entry<Point, MoveValueEnum>> entryList : movesGroupedByRow)
        {
            if (lookForWinnerInCollection(entryList))
            {
                return WinEnum.DONE;
            }
        }

        return WinEnum.NONE;
    }

    private static boolean lookForWinnerInCollection(final List<Map.Entry<Point, MoveValueEnum>> entries)
    {
        return entries.size() == BOARD_SIZE;

    }

}
