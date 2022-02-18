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
    private AddPayType addPayType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calcsettings_id")
    private CalcSettings calcSettings;
    private String addPayFund;
    private String numberOrder;
}
