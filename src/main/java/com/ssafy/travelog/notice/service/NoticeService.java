package com.ssafy.travelog.notice.service;

import com.ssafy.travelog.notice.dto.NoticeDto;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    int insert(Map<String, String> map);

    int update(Map<String, String> map);

    int delete(int noticeNo);

    List<NoticeDto> search(Map<String, String> map);

    List<NoticeDto> searchAll();

    NoticeDto searchByNo(int noticeNo);
}
