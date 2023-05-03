package com.ssafy.travelog.notice.service;

import com.ssafy.travelog.notice.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;

public class NoticeService {
    private NoticeDao noticeDao;

    @Autowired
    public NoticeService(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }
}
