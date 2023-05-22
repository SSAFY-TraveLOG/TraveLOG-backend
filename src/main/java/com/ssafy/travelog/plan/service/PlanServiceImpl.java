package com.ssafy.travelog.plan.service;

import com.ssafy.travelog.plan.dao.PlanDao;
import com.ssafy.travelog.plan.dto.TravelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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
}
