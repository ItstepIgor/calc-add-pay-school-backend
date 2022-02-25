package com.calcaddpayschoolbackend.dto;

import lombok.Data;

@Data
public class TimeSheetDTO {
    private long id;
    private long peopleId;
    private int actualDaysWorked;
    private long calcSettingsId;
}
