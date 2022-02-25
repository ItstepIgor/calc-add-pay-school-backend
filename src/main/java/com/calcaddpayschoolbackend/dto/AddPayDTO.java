package com.calcaddpayschoolbackend.dto;


import lombok.Data;

@Data
public class AddPayDTO {
    private long id;
    private long addPayTypeId;
    private String addPayCode;
    private String addPayName;
    private int maxPercent;
    private String description;
}
