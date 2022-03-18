package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddPayResultSumDTO {
    private BigDecimal bonusSum;
    private BigDecimal complicationSum;
    private BigDecimal motivationSum;
}
