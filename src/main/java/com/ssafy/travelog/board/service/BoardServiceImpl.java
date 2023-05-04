package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dao.BoardDao;
import com.ssafy.travelog.board.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public int writeArticle(Map<String, String> map) throws SQLException {
        return boardDao.writeArticle(map);
    }

    @Override
    @Transactional
    public BoardDto getArticle(Map<String, String> map) throws SQLException {
        boardDao.increaseReadCount(map);
        return boardDao.getArticle(map);
    }

    @Override
    public int modifyArticle(Map<String, String> map) throws SQLException {
        return boardDao.modifyArticle(map);
    }

    @Override
    public int deleteArticle(Map<String, String> map) throws SQLException {
        return boardDao.deleteArticle(map);
    }
}
