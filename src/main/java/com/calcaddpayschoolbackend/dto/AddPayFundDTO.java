package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class AddPayFundDTO {
    private long id;
//    private long addPayTypeId;
    @NotNull
    private AddPayTypeDTO addPayTypes;
//
//    private String addPayTypeName;
//    private long calcSettingId;
    private CalcSettingsDTO calcSettings;
//    @NotBlank
//    private LocalDate calcDate;
    @NotNull
    private BigDecimal addPayFunds;
    @NotBlank
    private String numberOrder;
}
