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
public class AddPayFund extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_pay_type_id")
    private AddPayType addPayTypes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calc_settings_id")
    private CalcSettings calcSettings;
    @Column(nullable = false)
    private BigDecimal addPayFunds;
    @Column(nullable = false)
    private String numberOrder;
}
