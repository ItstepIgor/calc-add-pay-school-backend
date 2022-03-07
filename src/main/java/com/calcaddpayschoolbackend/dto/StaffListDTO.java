package com.calcaddpayschoolbackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StaffListDTO {
    private long id;
    private long peopleId;
    private String peopleSurAndFirstName;
    private long positionId;
    private String positionName;
    private BigDecimal salary;
    private boolean youngSpecial;
    private boolean disabled;
}
