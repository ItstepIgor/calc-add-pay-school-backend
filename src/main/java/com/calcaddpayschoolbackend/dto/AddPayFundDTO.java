package com.calcaddpayschoolbackend.dto;

import lombok.Data;


@Data
public class AddPayFundDTO {
    private long id;
    private long addPayTypeId;
    private String addPayTypeName;
    private long calcSettingId;
    private String addPayFunds;
    private String numberOrder;
}
