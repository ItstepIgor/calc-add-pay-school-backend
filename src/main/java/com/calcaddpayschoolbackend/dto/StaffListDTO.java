package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StaffListDTO {
    private long id;
    private long peopleId;
//    private String peopleName;
    private long positionId;
//    private String positionName;
    private BigDecimal salary;
}
