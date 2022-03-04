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
    @JoinColumn(name = "add_pay_id")
    private AddPay addPays;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_list_id")
    private StaffList staffList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_sheet_id")
    private TimeSheet timeSheets;
    @Column(nullable = false)
    private double percent;
    @Column(nullable = false)
    private BigDecimal sum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_norms_id")
    private BasicNorms basicNorms;
}
