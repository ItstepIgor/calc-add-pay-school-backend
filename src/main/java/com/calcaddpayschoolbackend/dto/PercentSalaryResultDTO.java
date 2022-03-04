package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PercentSalaryResultDTO {
    private long id;
    private long staffListId;
    private long timeSheetId;
    private String peopleSurAndFirstName;
    private long percentSalaryId;
    private String percentSalaryName;
    private BigDecimal sum;
}
