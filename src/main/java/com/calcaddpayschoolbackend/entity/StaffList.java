package com.calcaddpayschoolbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long peopleId;
    private long positionId;
    private BigDecimal salary;
}
