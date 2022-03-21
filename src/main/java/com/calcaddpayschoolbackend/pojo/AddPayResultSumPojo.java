package com.calcaddpayschoolbackend.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddPayResultSumPojo {
    private BigDecimal bonusSum;
    private BigDecimal complicationSum;
    private BigDecimal motivationSum;
}
