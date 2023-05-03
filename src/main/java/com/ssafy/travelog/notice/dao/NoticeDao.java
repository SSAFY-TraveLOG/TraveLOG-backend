package com.ssafy.travelog.notice.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface NoticeDao {
    int insert(Map<String, String> map);

    int update(Map<String, String> map);

    int delete(int noticeNo);
}
