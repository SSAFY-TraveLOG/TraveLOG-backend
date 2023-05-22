package com.ssafy.travelog.plan.service;

import com.ssafy.travelog.plan.dao.PlanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl {
    private PlanDao planDao;

    @Autowired
    public PlanServiceImpl(PlanDao planDao) {
        this.planDao = planDao;
    }
}
