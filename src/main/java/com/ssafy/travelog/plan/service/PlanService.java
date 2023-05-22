package com.ssafy.travelog.plan.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.Map;

public interface PlanService {
    int createPlan(Map<String, Object> map) throws SQLException, JsonProcessingException;
}

