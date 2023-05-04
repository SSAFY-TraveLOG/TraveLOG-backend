package com.ssafy.travelog.board.dao;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface BoardDao {
    int writeArticle(Map<String,String> map) throws SQLException;

    int deleteArticle(Map<String, String> map) throws SQLException;
}
