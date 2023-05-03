package com.ssafy.travelog.notice.controller;

import com.ssafy.travelog.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService service) {
        this.noticeService = service;
    }

}
