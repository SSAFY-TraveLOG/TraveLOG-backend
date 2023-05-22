package com.ssafy.travelog.plan.dto;

import lombok.*;

@Getter
@Setter
public class RouteDto {
    private int routeId;
    private int planNo;
    private int contentId;
    private int travelOrder;
    private String visitDate;
    private String description;
    private String title;
    private int contentTypeId;
    private String address;
    private String image;
    private double latitude;
    private double longitude;
}
