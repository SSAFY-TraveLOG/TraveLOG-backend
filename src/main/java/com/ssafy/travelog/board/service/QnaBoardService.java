package com.ssafy.travelog.board.service;

import com.ssafy.travelog.board.dto.QnaBoardDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface QnaBoardService {
    int writeArticle(Map<String, String> map) throws SQLException;
}
