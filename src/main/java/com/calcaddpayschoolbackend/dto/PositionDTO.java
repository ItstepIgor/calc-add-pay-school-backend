package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PositionDTO {
    private long id;
    @NotBlank
    private String positionName;
    @NotBlank
    private int sorting;
}
