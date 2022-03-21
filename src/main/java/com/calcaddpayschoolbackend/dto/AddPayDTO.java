package com.calcaddpayschoolbackend.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddPayDTO {
    private long id;
    private long addPayTypeId;
    @NotBlank
    private String addPayTypeName;
    @NotBlank
    private String addPayCode;
    @NotBlank
    private String addPayName;
    @NotBlank
    private int maxPercent;
    private String description;
}
