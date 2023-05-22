package com.ssafy.travelog.plan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.travelog.plan.dto.PlanDto;
import com.ssafy.travelog.plan.dto.TravelDto;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;

public interface PlanService {
    int createPlan(Map<String, Object> map) throws SQLException, JsonProcessingException;
    List<TravelDto> listPlan(int userNo) throws SQLException;
    int deletePlan(int planNo) throws SQLException;
    PlanDto getPlan(int planNo) throws SQLException;
}
