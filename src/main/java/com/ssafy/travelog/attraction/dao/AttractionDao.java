package com.ssafy.travelog.attraction.dao;

import com.ssafy.travelog.attraction.dto.AttractionDto;
import com.ssafy.travelog.attraction.dto.GugunDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionDao {
    List<AttractionDto> searchByCondition(Map<String, String> map);

    AttractionDto attrDescription(int contentId);

    List<GugunDto> searchGugunBySido(int sidoCode);
}
