package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dao.QnaBoardDao;
import com.ssafy.travelog.board.dto.QnaBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class QnaBoardServiceImpl implements QnaBoardService {
    private QnaBoardDao boardDao;

    @Autowired
    public QnaBoardServiceImpl(QnaBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public int writeArticle(Map<String, String> map) throws SQLException {
        return boardDao.writeArticle(map);
    }

    @Override
    @Transactional
    public QnaBoardDto getArticle(Map<String, String> map) throws SQLException {
        return boardDao.getArticle(map);
    }

    @Override
    public int modifyArticle(Map<String, String> map) throws SQLException {
        return boardDao.modifyArticle(map);
    }
}
