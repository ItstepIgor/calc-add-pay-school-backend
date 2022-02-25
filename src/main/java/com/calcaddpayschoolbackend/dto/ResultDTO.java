package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResultDTO {

    private long id;
    private long addPayId;
    private long staffListId;
    private long timeSheetId;
    private long basicNormsId;
    private double percent;
    private BigDecimal sum;
}
