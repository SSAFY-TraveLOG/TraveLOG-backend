package com.ssafy.travelog.attraction.service;

import com.ssafy.travelog.attraction.dao.AttractionDao;
import com.ssafy.travelog.attraction.dto.AttractionDto;
import com.ssafy.travelog.attraction.dto.GugunDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AttractionServiceImpl implements AttractionService {
    private AttractionDao attractionDao;

    @Autowired
    public AttractionServiceImpl(AttractionDao attractionDao) {
        this.attractionDao = attractionDao;
    }

    @Override
    public List<AttractionDto> searchByCondition(Map<String, String> map) {
        return attractionDao.searchByCondition(map);
    }

    @Override
    public AttractionDto attrDescription(int contentId) {
        return attractionDao.attrDescription(contentId);
    }

    @Override
    public List<GugunDto> searchGugunBySido(int sidoCode) {
        return attractionDao.searchGugunBySido(sidoCode);
    }
}
