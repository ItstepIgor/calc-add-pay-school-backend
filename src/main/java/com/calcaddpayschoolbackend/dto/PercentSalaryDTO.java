package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PercentSalaryDTO {
    private long id;
    private LocalDate percentStartDate;
    private int percentSalaryAll;
    private int percentSalaryForYoungSpecial;
}
