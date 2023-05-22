package com.ssafy.travelog.plan.dao;

import com.ssafy.travelog.plan.dto.ParticipantDto;
import com.ssafy.travelog.plan.dto.PlanDto;
import com.ssafy.travelog.plan.dto.RouteDto;
import com.ssafy.travelog.plan.dto.TravelDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlanDao {
    List<TravelDto> listPlan(int userNo) throws SQLException;
    int deletePlanByPlanNo(int planNo) throws SQLException;
    int deleteParticipantsByPlanNo(int planNo) throws SQLException;
    int deleteRoutesByPlanNo(int planNo) throws SQLException;
    PlanDto getPlan(int planNo) throws SQLException;
    List<ParticipantDto> getParticipants(int planNo) throws SQLException;
    List<RouteDto> getRoutes(int planNo) throws SQLException;
}
