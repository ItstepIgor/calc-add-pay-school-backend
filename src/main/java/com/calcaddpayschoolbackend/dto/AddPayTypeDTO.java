package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddPayTypeDTO {
    private long id;
    @NotBlank
    private String addPayTypeName;
}
