package com.ssafy.travelog.user.service;

import com.ssafy.travelog.user.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDto> getAllUser() throws SQLException;
    UserDto getUserInfo(int userNo) throws SQLException;
    Boolean checkPassword(Map<String, String> param) throws SQLException;
    int modifyUser(Map<String, String> param) throws SQLException;
    int deleteUser(Map<String, String> param) throws SQLException;
}
