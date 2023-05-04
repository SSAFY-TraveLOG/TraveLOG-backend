package com.ssafy.travelog.user.dao;

import com.ssafy.travelog.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface UserDao {
    int modifyUser(Map<String, String> param) throws SQLException;
    int deleteUser(Map<String, String> param) throws SQLException;
}
