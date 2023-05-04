package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dao.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
