package com.ssafy.travelog.user.service;

import com.ssafy.travelog.user.dao.UserDao;

import com.ssafy.travelog.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUser() throws SQLException {
        return userDao.getAllUser();
    }

    @Override
    public UserDto getUserInfo(int userNo) throws SQLException {
        return userDao.getUserInfo(userNo);
    }

    @Override
    public Boolean checkPassword(Map<String, String> map) throws SQLException {
        return passwordEncoder.matches(map.get("password"), userDao.checkPassword(map).getPassword());
    }

    @Override
    public int modifyUser(Map<String, String> map) throws SQLException {
        if (map.get("password") != null) {
            String encodePw = passwordEncoder.encode(map.get("password"));
            map.put("password", encodePw);
        }
        return userDao.modifyUser(map);
    }

    @Override
    public int deleteUser(Map<String, String> map) throws SQLException {
        if (map.get("password") != null) {
            Boolean valid = passwordEncoder.matches(map.get("password"), getUserInfo(Integer.parseInt(map.get("userNo"))).getPassword());
            if (valid)
                return userDao.deleteUser(map);
            else
                return 0;
        }
        return 0;
    }
}
