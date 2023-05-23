package com.ssafy.travelog.plan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.travelog.plan.dao.PlanDao;
import com.ssafy.travelog.plan.dto.PlanDto;
import com.ssafy.travelog.plan.dto.TravelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {
    private PlanDao planDao;

    @Autowired
    public PlanServiceImpl(PlanDao planDao) {
        this.planDao = planDao;
    }

    @Override
    public List<TravelDto> listPlan(int userNo) throws SQLException {
        return planDao.listPlan(userNo);
    }

    @Override
    public int deletePlan(int planNo) throws SQLException {
        return planDao.deletePlanByPlanNo(planNo) + planDao.deleteParticipantsByPlanNo(planNo) + planDao.deleteRoutesByPlanNo(planNo);
    }

    @Override
    public PlanDto getPlan(int planNo) throws SQLException {
        PlanDto plan = planDao.getPlan(planNo);
        plan.setParticipants(planDao.getParticipants(planNo));
        plan.setRoutes(planDao.getRoutes(planNo));
        return plan;
    }

    @Override
    @Transactional
    public int createPlan(Map<String, Object> map) throws SQLException, JsonProcessingException {
        // 여행 정보 먼저 plan table에 insert 하고 id 받아오기
        map.put("plan_no", 0);
        planDao.createPlan(map);
        int planId = (int) map.get("plan_no");

        //그 id로 participant table에 insert하기
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonArray = objectMapper.writeValueAsString(map.get("participants"));
        List<Map<String, Object>> participantList = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {});

        for (int i = 0; i < participantList.size(); i++) {
            participantList.get(i).put("planNo", planId);
        }

        Map<String, Object> host = new HashMap<>();

        host.put("planNo", planId);
        host.put("participantNo",map.get("hostNo"));
        host.put("authority",1);

        participantList.add(host);

        planDao.insertParticipants(participantList);

        //그 id로 route table에 insert하기
        jsonArray = objectMapper.writeValueAsString(map.get("routes"));
        List<Map<String, Object>> routeList = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {});

        for (int i = 0; i < routeList.size(); i++) {
            routeList.get(i).put("planNo", planId);
        }

        planDao.insertRoutes(routeList);

        return planId;
    }

    @Override
    @Transactional
    public int modifyPlan(Map<String, Object> map) throws SQLException, JsonProcessingException {
        try{
            int planNo = (Integer) map.get("planNo");
            // plan 업데이트
            planDao.modifyPlan(map);
            ObjectMapper objectMapper = new ObjectMapper();

            if(map.get("participants") != null ){
                // delete participant
                planDao.deleteParticipantsByPlanNoExcludingHost(map);

                String jsonArray = objectMapper.writeValueAsString(map.get("participants"));

                List<Map<String, Object>> participantList = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {});

                for (int i = 0; i < participantList.size(); i++) {
                    participantList.get(i).put("planNo", planNo);
                }
                planDao.insertParticipants(participantList);
            }

            if(map.get("routes") != null){
                planDao.deleteRoutesByPlanNo(planNo);
                // insert route
                String jsonArray = objectMapper.writeValueAsString(map.get("routes"));
                List<Map<String, Object>> routeList = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {});

                for (int i = 0; i < routeList.size(); i++) {
                    routeList.get(i).put("planNo", planNo);
                }

                planDao.insertRoutes(routeList);
            }
            return 1;
        } catch (Exception e){
            return 0;
        }

    }
}
