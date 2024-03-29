package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dto.CommentDto;
import com.ssafy.travelog.board.dto.QnaBoardDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface QnaBoardService {
    int writeArticle(Map<String, String> map) throws SQLException;

    QnaBoardDto getArticle(Map<String, String> map) throws SQLException;

    int modifyArticle(Map<String, String> map) throws SQLException;

    int deleteArticle(Map<String, String> map) throws SQLException;

    List<QnaBoardDto> search(Map<String, String> map) throws SQLException;

    List<CommentDto> searchAll(int articleNo) throws SQLException;

    int writeComment(Map<String, String> map) throws SQLException;

    int updateComment(Map<String, String> map) throws SQLException;

    int deleteComment(int commentId) throws SQLException;
}
