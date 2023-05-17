package com.ssafy.travelog.auth.service;

import com.ssafy.travelog.user.dto.UserDto;
import com.ssafy.travelog.util.jwt.TokenInfo;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.SQLException;
import java.util.Map;

public interface AuthService {
    int join(Map<String, String> map) throws SQLException;
    UserDto login(Map<String, String> map) throws SQLException;
    int checkId(Map<String, String> map) throws SQLException;
    int checkEmail(Map<String, String> map) throws SQLException;

    TokenInfo getToken(Map<String, String> map) throws SQLException ;

//    UserDetails createUserDetails(UserDto user);
}
