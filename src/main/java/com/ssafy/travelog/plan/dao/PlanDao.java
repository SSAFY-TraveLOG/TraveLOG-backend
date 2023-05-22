package com.ssafy.travelog.plan.dao;

import com.ssafy.travelog.plan.dto.TravelDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlanDao {
    List<TravelDto> listPlan(int userNo) throws SQLException;
}
