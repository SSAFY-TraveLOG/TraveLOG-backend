package com.ssafy.travelog.plan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.travelog.plan.dao.PlanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
    @Transactional
    public int createPlan(Map<String, Object> map) throws SQLException, JsonProcessingException {
        // 여행 정보 먼저 plan table에 insert 하고 id 받아오기
        map.put("plan_no", 0);
        planDao.createPlan(map);
        int planId = (int) map.get("plan_no");
        System.out.println("planId" + planId);

        //그 id로 participant table에 insert하기
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonArray = objectMapper.writeValueAsString(map.get("participants"));
        List<Map<String, Object>> participantList = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {});

        for (int i = 0; i < participantList.size(); i++) {
            participantList.get(i).put("planNo",planId );
        }

        planDao.insertParticipants(participantList);

        //그 id로 route table에 insert하기

        jsonArray = objectMapper.writeValueAsString(map.get("routes"));
        List<Map<String, Object>> routeList = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {});

        for (int i = 0; i < routeList.size(); i++) {
            routeList.get(i).put("planNo",planId );
        }

        planDao.insertRoutes(routeList);

        return planId;
    }
}
