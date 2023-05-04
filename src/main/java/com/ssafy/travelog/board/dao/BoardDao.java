package com.ssafy.travelog.board.dao;

import com.ssafy.travelog.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    int writeArticle(Map<String,String> map) throws SQLException;

    int modifyArticle(Map<String, String> map) throws SQLException;

    BoardDto getArticle(Map<String, String> map) throws SQLException;

    int increaseReadCount(Map<String, String> map) throws SQLException;

    int deleteArticle(Map<String, String> map) throws SQLException;

    List<BoardDto> searchArticle(Map<String, String> map) throws SQLException;
}
