package com.ssafy.travelog.user.service;

import com.ssafy.travelog.user.dao.UserDao;

import com.ssafy.travelog.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int modifyUser(Map<String, String> map) throws SQLException {
        return userDao.modifyUser(map);
    }

    @Override
    public int deleteUser(Map<String, String> map) throws SQLException {
        return userDao.deleteUser(map);
    }
}
