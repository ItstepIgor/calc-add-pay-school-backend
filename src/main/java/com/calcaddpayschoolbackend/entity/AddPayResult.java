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
public class AddPayResult extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_pay_id", nullable = false)
    private AddPay addPay;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_list_id", nullable = false)
    private StaffList staffList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_sheet_id", nullable = false)
    private TimeSheet timeSheets;
    @Column(nullable = false)
    private double percent;
    @Column(nullable = false)
    private BigDecimal sum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_norms_id", nullable = false)
    private BasicNorms basicNorms;
}
