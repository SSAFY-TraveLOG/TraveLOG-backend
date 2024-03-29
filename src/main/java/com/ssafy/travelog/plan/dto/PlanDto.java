package com.ssafy.travelog.plan.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class PlanDto {
    private int planNo;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String sidoName;
    private int sidoCode;
    private int gugunCode;
    private int authority;
    private int hostNo;
    private List<ParticipantDto> participants;
    private List<RouteDto> routes;
}
