package com.ssafy.travelog.plan.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class PlanDto {
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String sidoName;
    private List<ParticipantDto> participants;

    private List<RouteDto> routes;
}
