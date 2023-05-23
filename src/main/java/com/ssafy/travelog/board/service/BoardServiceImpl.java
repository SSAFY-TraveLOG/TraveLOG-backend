package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dao.BoardDao;
import com.ssafy.travelog.board.dao.CommentDao;
import com.ssafy.travelog.board.dto.BoardDto;
import com.ssafy.travelog.board.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardDao boardDao;
    private CommentDao commentDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao, CommentDao commentDao) {
        this.boardDao = boardDao;
        this.commentDao = commentDao;
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

    @Override
    public List<BoardDto> search(Map<String, String> map) throws SQLException {
        return boardDao.searchArticle(map);
    }

    @Override
    public List<CommentDto> searchAll(Map<String, String> map) throws SQLException {
        return commentDao.searchAll(map);
    }

    @Override
    public int writeComment(Map<String, String> map) throws SQLException {
        return commentDao.writeComment(map);
    }

    @Override
    public int updateComment(Map<String, String> map) {
        return commentDao.updateComment(map);
    }
}
