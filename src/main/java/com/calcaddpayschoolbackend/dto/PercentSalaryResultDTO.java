package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PercentSalaryResultDTO {
    private long id;
    private long staffListId;
    private String peopleSurAndFirstName;
    private String positionName;
    private long timeSheetId;
    private LocalDate calcDate;
    private long percentSalaryId;
    private int percent;
    private BigDecimal sum;
}
