package com.ssafy.travelog.plan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PlanDao {
    int createPlan(Map<String, Object> map) throws SQLException;
    int insertParticipants(List<Map<String, Object>> list) throws SQLException;
    int insertRoutes(List<Map<String, Object>> list) throws SQLException;
}
