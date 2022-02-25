package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BasicNormsDTO {
    private long id;
    private String basicNormName;
    private BigDecimal basicNormValue;
    private LocalDate basicNormDate;
}
