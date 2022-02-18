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
public class AddPay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_pay_type_id")
    private AddPayType addPayType;
    @Column(nullable = false)
    private String addPayCode;
    @Column(nullable = false)
    private String addPayName;
    private int maxPercent;
    @Column(nullable = false)
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "addPay")
    private Set<Result> results;

}
