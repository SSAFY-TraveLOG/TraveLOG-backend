package com.ssafy.travelog.board.service;

import java.sql.SQLException;
import java.util.Map;

public interface BoardService {
    int writeArticle(Map<String, String> map) throws SQLException;
}
