package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class AddPayFundDTO {
    private long id;
    private long addPayTypeId;
    private String addPayTypeName;
    private long calcSettingId;
    private LocalDate calcDate;
    private BigDecimal addPayFunds;
    private String numberOrder;
}
