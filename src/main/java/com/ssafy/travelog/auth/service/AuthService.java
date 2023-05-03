package com.ssafy.travelog.auth.service;

import com.ssafy.travelog.auth.dto.UserDto;

import java.sql.SQLException;
import java.util.Map;

public interface AuthService {
    int join(Map<String, String> map) throws SQLException;
    UserDto login(Map<String, String> map) throws SQLException;
    int checkId(Map<String, String> map) throws SQLException;
    int checkEmail(Map<String, String> map) throws SQLException;
}
