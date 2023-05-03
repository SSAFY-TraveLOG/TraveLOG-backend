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

    @PostMapping("/join")
    public ResponseEntity<Message> join(@RequestBody Map<String, String> map){

        try{
            int ret = authService.join(map);

            if(ret == 1) {

                UserDto userDto = new UserDto();
                userDto.setUserId(map.get("userId"));
                userDto.setUserName(map.get("userName"));
                userDto.setEmailId(map.get("emailId"));
                userDto.setEmailDomain(map.get("emailDomain"));


                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(userDto);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
           return exceptionHandling(e);
        }

    }

    @PostMapping("/check")
    public ResponseEntity<Message> login(@RequestBody Map<String, String> map){

        try{
            UserDto ret = authService.login(map);

            if(ret != null) {
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return exceptionHandling(e);
        }

    }

    private ResponseEntity<Message> exceptionHandling(Exception e) {
        e.printStackTrace();
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.NOT_FOUND);
        message.setCode(StatusEnum.NOT_FOUND);
        message.setMessage("요청에 실패하였습니다.");
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

}
