package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class AddPayFundDTO {
    private long id;
//    private long addPayTypeId;
    @NotBlank
    private AddPayTypeDTO addPayTypes;
//
//    private String addPayTypeName;
//    private long calcSettingId;
    @NotBlank
    private CalcSettingsDTO calcSettings;
//    @NotBlank
//    private LocalDate calcDate;
    @NotBlank
    private BigDecimal addPayFunds;
    @NotBlank
    private String numberOrder;
}
