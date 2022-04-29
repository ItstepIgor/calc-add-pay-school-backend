package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeSheetDTO {
    private long id;
    private long peopleId;
    private String peopleSurAndFirstName;
    private int actualDaysWorked;
    private long calcSettingsId;
    private LocalDate calcDate;
}
