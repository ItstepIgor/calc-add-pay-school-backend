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
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_pay_id")
    private AddPay addPay;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_list_id")
    private StaffList staffList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_sheet_id")
    private TimeSheet timeSheet;
    private int percent;
    private BigDecimal sum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_norms_id")
    private BasicNorms basicNorms;
}
