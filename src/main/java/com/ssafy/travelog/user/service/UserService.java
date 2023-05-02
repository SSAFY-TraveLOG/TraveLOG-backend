package com.ssafy.travelog.user.service;

import com.ssafy.travelog.user.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public interface UserService {
    int modifyUser(UserDto user);
    int deleteUser(UserDto user);
}
