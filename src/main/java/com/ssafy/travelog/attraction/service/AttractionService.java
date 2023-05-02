package com.ssafy.travelog.attraction.service;

import com.ssafy.travelog.attraction.dao.AttractionDao;
import com.ssafy.travelog.attraction.dto.AttractionDto;

import java.util.List;
import java.util.Map;

public interface AttractionService {
    List<AttractionDto> searchByCondition(Map<String, String> map);
}
