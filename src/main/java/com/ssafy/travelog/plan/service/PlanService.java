package com.ssafy.travelog.plan.service;

import com.ssafy.travelog.plan.dto.TravelDto;

import java.sql.SQLException;
import java.util.List;

public interface PlanService {
    List<TravelDto> listPlan(int userNo) throws SQLException;
    int deletePlan(int planNo) throws SQLException;
}
