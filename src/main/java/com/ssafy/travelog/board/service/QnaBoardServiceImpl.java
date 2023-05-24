package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dao.CommentDao;
import com.ssafy.travelog.board.dao.QnaBoardDao;
import com.ssafy.travelog.board.dto.CommentDto;
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
    private CommentDao commentDao;

    @Autowired
    public QnaBoardServiceImpl(QnaBoardDao boardDao, CommentDao commentDao) {
        this.boardDao = boardDao;
        this.commentDao = commentDao;
    }

    @Override
    public int writeArticle(Map<String, String> map) throws SQLException {
        return boardDao.writeArticle(map);
    }

    @Override
    @Transactional
    public QnaBoardDto getArticle(Map<String, String> map) throws SQLException {
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
    public List<QnaBoardDto> search(Map<String, String> map) throws SQLException {
        return boardDao.searchArticle(map);
    }

    @Override
    public List<CommentDto> searchAll(int articleNo) throws SQLException {
        return commentDao.qnaSearchAll(articleNo);
    }

    @Override
    public int writeComment(Map<String, String> map) throws SQLException {
        return commentDao.qnaWriteComment(map);
    }

    @Override
    public int updateComment(Map<String, String> map) throws SQLException {
        return commentDao.qnaUpdateComment(map);
    }

    @Override
    public int deleteComment(int commentId) throws SQLException {
        return commentDao.qnaDeleteComment(commentId);
    }
}
