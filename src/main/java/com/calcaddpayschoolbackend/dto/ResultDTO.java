package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResultDTO {

    private long id;
    private long addPayId;
    private long staffListId;
    private long timeSheetId;
    private String peopleSurAndFirstName;
    private long basicNormsId;
    private String basicNormName;
    private double percent;
    private BigDecimal sum;
}
