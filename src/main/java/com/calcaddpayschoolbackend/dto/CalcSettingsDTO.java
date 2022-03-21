package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class CalcSettingsDTO {
    private long id;
    @NotBlank
    private LocalDate calcDate;
    @Min(1)
    @Max(31)
    private int workingDays;
}
