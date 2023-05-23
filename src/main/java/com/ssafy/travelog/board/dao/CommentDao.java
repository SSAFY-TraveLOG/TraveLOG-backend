package com.ssafy.travelog.board.dao;

import com.ssafy.travelog.board.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommentDao {
    List<CommentDto> searchAll(Map<String, String> map) throws SQLException;

    int writeComment(Map<String, String> map) throws SQLException;

    int updateComment(Map<String, String> map);

    int deleteComment(int replyId);
}
