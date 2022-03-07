package com.calcaddpayschoolbackend.dto;

import lombok.Data;

@Data
public class
PeopleDTO {
    private long id;
    private String surName;
    private String firstName;
    private String patronymic;
    private String personnelNumber;
    private String phoneNumber;
    private String address;
}
