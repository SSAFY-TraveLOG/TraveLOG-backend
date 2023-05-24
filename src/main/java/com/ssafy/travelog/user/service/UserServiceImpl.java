package com.ssafy.travelog.user.service;

import com.ssafy.travelog.user.dao.UserDao;

import com.ssafy.travelog.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> getAllUser() throws SQLException {
        return userDao.getAllUser();
    }

    @Override
    public int modifyUser(Map<String, String> map) throws SQLException {
        if(map.get("password") != null){
            String encodePw = passwordEncoder.encode(map.get("password"));
            map.put("password", encodePw);
        }
        return userDao.modifyUser(map);
    }

    @Override
    public int deleteUser(Map<String, String> map) throws SQLException {
        return userDao.deleteUser(map);
    }
}
