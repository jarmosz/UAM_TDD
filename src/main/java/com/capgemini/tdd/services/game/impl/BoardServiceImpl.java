package com.capgemini.tdd.services.game.impl;

import com.capgemini.tdd.dao.BoardDao;
import com.capgemini.tdd.dao.entities.BoardBE;
import com.capgemini.tdd.services.game.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService
{


    @Autowired
    private BoardDao boardDao;

    @Override
    public BoardBE findById(final Long id)
    {
        return boardDao.findById(id);
    }

    @Override
    public List<BoardBE> findAll()
    {
        return boardDao.findAll();
    }

    @Override
    public BoardBE save(final BoardBE boardBE)
    {
        Long id = findAll().stream().map(x -> x.getId()).max(Long :: compare).orElse(null);
        if(id == null) id = 0L;
        else id = id+1;
        boardBE.setId(id);
        return boardDao.save(boardBE);
    }

    @Override
    public BoardBE findByPlayersNames(final String playerOne, final String playerTwo)
    {
        return boardDao.getByPlayersNames(playerOne, playerTwo);
    }
}
