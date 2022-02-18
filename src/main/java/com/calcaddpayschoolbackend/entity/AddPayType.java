package com.calcaddpayschoolbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPayType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    String addPayTypeName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addPayTypes")
    private Set<AddPayFund> addPayFunds;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addPayTypes")
    private Set<AddPay> addPay;
}
