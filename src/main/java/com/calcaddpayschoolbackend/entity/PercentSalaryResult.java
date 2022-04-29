package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercentSalaryResult extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_sheet_id", nullable = false)
    private TimeSheet timeSheets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "percent_salary_id", nullable = false)
    private PercentSalary percentSalary;
    @Column(nullable = false)
    private int percent;
    @Column(nullable = false)
    private BigDecimal sum;
}
