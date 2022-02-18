package com.calcaddpayschoolbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPayFund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_pay_type_id")
    private AddPayType addPayTypes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calcsettings_id")
    private CalcSettings calcSettings;
    @Column(nullable = false)
    private String addPayFunds;
    @Column(nullable = false)
    private String numberOrder;
}
