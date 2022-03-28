package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BasicNormsDTO {
    private long id;
    @NotBlank
    private String basicNormName;
    @Min(1)
    private BigDecimal basicNormValue;
    @NotNull
    private LocalDate basicNormDate;
}
