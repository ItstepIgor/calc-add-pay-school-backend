package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AddPayResultDTO {

    private long id;
    private long addPayId;
    private String addPayCode;
    private long staffListId;
    private String peopleSurAndFirstName;
    private String positionName;
    private long timeSheetId;
    private LocalDate calcDate;
    private long basicNormsId;
    private String basicNormName;
    @Min(0)
    @Max(100)
    private double percent;
    private BigDecimal sum;
}
