package com.ssafy.travelog.attraction.dao;

import com.ssafy.travelog.attraction.dto.AttractionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionDao {
    List<AttractionDto> searchByCondition(Map<String, String> map);
}
