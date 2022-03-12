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
public class AddPay extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_pay_type_id")
    private AddPayType addPayTypes;
    @Column(nullable = false)
    private String addPayCode;
    @Column(nullable = false)
    private String addPayName;
    private int maxPercent;
    @Column(nullable = false)
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addPay")
    private Set<AddPayResult> addPayResults;

}
