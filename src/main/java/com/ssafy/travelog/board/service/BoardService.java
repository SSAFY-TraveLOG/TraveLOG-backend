package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dto.BoardDto;
import com.ssafy.travelog.board.dto.CommentDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BoardService {
    int writeArticle(Map<String, String> map) throws SQLException;

    BoardDto getArticle(Map<String, String> map) throws SQLException;

    int modifyArticle(Map<String, String> map) throws SQLException;

    int deleteArticle(Map<String, String> map) throws SQLException;

    List<BoardDto> search(Map<String, String> map) throws SQLException;

    List<CommentDto> searchAll(Map<String, String> map) throws SQLException;

    int writeComment(Map<String, String> map) throws SQLException;
}
