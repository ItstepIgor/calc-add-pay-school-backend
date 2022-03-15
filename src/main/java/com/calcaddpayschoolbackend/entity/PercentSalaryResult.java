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
    @JoinColumn(name = "staff_list_id")
    private StaffList staffList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_sheet_id")
    private TimeSheet timeSheets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "percent_salary_id")
    private PercentSalary percentSalary;
    @Column(nullable = false)
    private int percent;
    @Column(nullable = false)
    private BigDecimal sum;
}
