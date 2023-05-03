package com.ssafy.travelog.notice.service;

import com.ssafy.travelog.notice.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface NoticeService {
    int insert(Map<String, String> map);
}
