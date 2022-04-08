package com.calcaddpayschoolbackend.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResultAllSumForMonthPojo {
    private BigDecimal bonusSum;
    private String bonusName;
    private BigDecimal complicationSum;
    private String complicationName;
    private BigDecimal motivationSum;
    private String motivationName;
}
