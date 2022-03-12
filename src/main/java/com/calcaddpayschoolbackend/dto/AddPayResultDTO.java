package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddPayResultDTO {

    private long id;
    private long addPayId;
    private String addPayCode;
    private long staffListId;
    private String peopleSurAndFirstName;
    private String positionName;
    private long timeSheetId;
    private long basicNormsId;
    private String basicNormName;
    private double percent;
    private BigDecimal sum;
}
