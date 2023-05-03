package com.ssafy.travelog.notice.service;

import java.util.Map;

public interface NoticeService {
    int insert(Map<String, String> map);

    int update(Map<String, String> map);

    int delete(int noticeNo);
}
