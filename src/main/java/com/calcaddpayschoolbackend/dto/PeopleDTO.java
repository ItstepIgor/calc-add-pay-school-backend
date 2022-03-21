package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class
PeopleDTO {
    private long id;
    @NotBlank
    private String surName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String patronymic;
    @NotBlank
    private String personnelNumber;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
}
