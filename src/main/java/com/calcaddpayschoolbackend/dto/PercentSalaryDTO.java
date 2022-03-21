package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PercentSalaryDTO {
    private long id;
    @NotBlank
    private LocalDate percentStartDate;
    @NotBlank
    private int percentSalaryAll;
    @NotBlank
    private int percentSalaryForYoungSpecial;
}
