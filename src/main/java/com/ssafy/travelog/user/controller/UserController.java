package com.ssafy.travelog.user.controller;

import com.ssafy.travelog.user.dto.UserDto;
import com.ssafy.travelog.user.service.UserService;
import com.ssafy.travelog.util.Message;
import com.ssafy.travelog.util.StatusEnum;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "모든 유저의 목록을 얻어옵니다.", response = List.class)
    public ResponseEntity<Message> getAllUser(){
        List<UserDto> ret = null;
        try {
            ret = userService.getAllUser();
            if(ret != null){
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PatchMapping("/modify")
    @ApiOperation(value = "유저 정보를 수정합니다.", response = UserDto.class)
    public ResponseEntity<Message> modifyUser(@RequestBody Map<String, String> map) {

        try {
            int ret = userService.modifyUser(map);
            if(ret != 0){
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }

    }

    @GetMapping("/{user-no}")
    @ApiOperation(value = "특정 유저의 정보를 얻어옵니다.", response = UserDto.class)
    public ResponseEntity<Message> getUserInfo(@PathVariable("user-no") int userNo){
        UserDto ret = null;
        try {
            ret = userService.getUserInfo(userNo);
            if(ret != null){
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PatchMapping("/delete")
    @ApiOperation(value = "유저 정보를 삭제합니다.", response = UserDto.class)
    public ResponseEntity<Message> deleteUser(@RequestBody Map<String, String> map) {

        try {
            int ret = userService.deleteUser(map);
            if(ret != 0){
                Message message = new Message();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                message.setStatus(StatusEnum.OK);
                message.setCode(StatusEnum.OK);
                message.setMessage("요청에 성공하였습니다.");
                message.setData(ret);

                return new ResponseEntity<>(message, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }

    }


    private ResponseEntity<Message> exceptionHandling(Exception e) {
        e.printStackTrace();
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
        message.setCode(StatusEnum.INTERNAL_SERVER_ERROR);
        message.setMessage("요청에 실패하였습니다.");
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
