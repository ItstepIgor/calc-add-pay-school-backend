package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CalcSettingsDTO {
    private long id;
    private LocalDate calcDate;
    private int workingDays;
}
