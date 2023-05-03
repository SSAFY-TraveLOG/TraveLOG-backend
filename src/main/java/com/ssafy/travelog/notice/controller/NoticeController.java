package com.ssafy.travelog.notice.controller;

import com.ssafy.travelog.notice.service.NoticeService;
import com.ssafy.travelog.util.Message;
import com.ssafy.travelog.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService service) {
        this.noticeService = service;
    }

    @PostMapping("/article")
    public ResponseEntity<Message> insert(@RequestParam Map<String, String> map) {
        try {
            int ret = noticeService.insert(map);

            if (ret != 0) {
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
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<Message> exceptionHandling(Exception e) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.INTERNAL_SERER_ERROR);
        message.setCode(StatusEnum.INTERNAL_SERER_ERROR);
        message.setMessage("요청에 실패하였습니다.");
        message.setData(e.getMessage());

        e.printStackTrace();

        return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
