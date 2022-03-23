package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@ToString(exclude = {"addPayResults"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPay extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_pay_type_id", nullable = false)
    private AddPayType addPayTypes;
    @Column(nullable = false)
    private String addPayCode;
    @Column(nullable = false)
    private String addPayName;
    @Column(nullable = false)
    private int maxPercent;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addPay")
    private Set<AddPayResult> addPayResults;

}
