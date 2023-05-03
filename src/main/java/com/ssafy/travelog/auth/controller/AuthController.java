package com.ssafy.travelog.auth.controller;

import com.ssafy.travelog.auth.dto.UserDto;
import com.ssafy.travelog.auth.service.AuthService;
import com.ssafy.travelog.util.Message;
import com.ssafy.travelog.util.StatusEnum;
import org.apache.catalina.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@MapperScan(basePackages = {"com.ssafy.travelog.auth.dao"})
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

}
