package com.ssafy.travelog.auth.service;

import com.ssafy.travelog.auth.dao.AuthDao;
import com.ssafy.travelog.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService{

    private AuthDao authDao;

    @Autowired
    public AuthServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public int join(Map<String, String> map) throws SQLException {
        return authDao.join(map);
    }

    @Override
    public UserDto login(Map<String, String> map) throws SQLException {
        return authDao.login(map);
    }

    @Override
    public int checkId(Map<String, String> map) throws SQLException {
        return authDao.checkId(map);
    }

    @Override
    public int checkEmail(Map<String, String> map) throws SQLException {
        return authDao.checkEmail(map);
    }
}
