package com.ssafy.travelog.notice.service;

import com.ssafy.travelog.notice.dao.NoticeDao;
import com.ssafy.travelog.notice.dto.NoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;

    @Autowired
    public NoticeServiceImpl(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public int insert(Map<String, String> map) {
        return noticeDao.insert(map);
    }

    @Override
    public int update(Map<String, String> map) {
        return noticeDao.update(map);
    }

    @Override
    public int delete(int noticeNo) {
        return noticeDao.delete(noticeNo);
    }

    @Override
    public List<NoticeDto> search(Map<String, String> map) {
        return noticeDao.search(map);
    }

    @Override
    public List<NoticeDto> searchAll() {
        return noticeDao.searchAll();
    }

    @Override
    public NoticeDto searchByNo(int noticeNo) {
        return noticeDao.searchByNo(noticeNo);
    }
}
