package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PercentSalaryDTO {
    private long id;
    @NotNull
    private LocalDate percentStartDate;
    @Min(1)
    private int percentSalaryAll;
    @Min(1)
    private int percentSalaryForYoungSpecial;
    @NotBlank
    private String percentYoungSpecialCode;
}
