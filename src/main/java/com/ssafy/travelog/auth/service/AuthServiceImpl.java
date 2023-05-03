package com.ssafy.travelog.auth.service;

import com.ssafy.travelog.auth.dao.AuthDao;
import com.ssafy.travelog.auth.dto.UserDto;
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
}
