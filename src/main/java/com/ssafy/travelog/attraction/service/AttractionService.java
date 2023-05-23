package com.ssafy.travelog.attraction.service;

import com.ssafy.travelog.attraction.dao.AttractionDao;
import com.ssafy.travelog.attraction.dto.AttractionDto;
import com.ssafy.travelog.attraction.dto.GugunDto;

import java.util.List;
import java.util.Map;

public interface AttractionService {
    List<AttractionDto> searchByCondition(Map<String, String> map);

    AttractionDto attrDescription(int contentId);

    List<GugunDto> searchGugunBySido(int sidoCode);
}
