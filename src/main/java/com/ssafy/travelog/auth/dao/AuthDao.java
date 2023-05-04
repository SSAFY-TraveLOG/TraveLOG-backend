package com.ssafy.travelog.auth.dao;

import com.ssafy.travelog.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface AuthDao {
    int join(Map<String, String> map) throws SQLException;
    UserDto login(Map<String, String> map) throws SQLException;
    int checkId(Map<String, String> map) throws SQLException;
    int checkEmail(Map<String, String> map) throws SQLException;
}
