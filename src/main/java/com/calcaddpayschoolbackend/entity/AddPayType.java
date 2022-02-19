package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPayType extends AbstractEntity {

    @Column(nullable = false)
    private String addPayTypeName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addPayTypes")
    private Set<AddPayFund> addPayFunds;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addPayTypes")
    private Set<AddPay> addPay;
}
