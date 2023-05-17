package com.ssafy.travelog.board.dao;

import com.ssafy.travelog.board.dto.QnaBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface QnaBoardDao {
    int writeArticle(Map<String,String> map) throws SQLException;

    QnaBoardDto getArticle(Map<String, String> map) throws SQLException;

    int increaseReadCount(Map<String, String> map) throws SQLException;

    int modifyArticle(Map<String, String> map) throws SQLException;

    int deleteArticle(Map<String, String> map) throws SQLException;
}
