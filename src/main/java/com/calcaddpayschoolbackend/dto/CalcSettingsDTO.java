package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CalcSettingsDTO {
    private long id;
    @NotNull
    private LocalDate calcDate;
    @Min(1)
    @Max(31)
    private int workingDays;
}
