package com.calcaddpayschoolbackend.dto;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AddPayDTO {
    private long id;
    private long addPayTypeId;
    private String addPayTypeName;
    @NotBlank
    private String addPayCode;
    @NotBlank
    private String addPayName;
    @Min(1)
    @Max(100)
    private int maxPercent;
    private String description;
}
