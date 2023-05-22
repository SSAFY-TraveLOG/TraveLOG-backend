package com.ssafy.travelog.plan.dto;

import lombok.*;

@Getter
@Setter
public class TravelDto {
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String sidoName;
    private int authority;
}
