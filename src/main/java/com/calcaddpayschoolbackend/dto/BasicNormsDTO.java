package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BasicNormsDTO {
    private long id;
    @NotBlank
    private String basicNormName;
    @NotBlank
    private BigDecimal basicNormValue;
    @NotBlank
    private LocalDate basicNormDate;
}
