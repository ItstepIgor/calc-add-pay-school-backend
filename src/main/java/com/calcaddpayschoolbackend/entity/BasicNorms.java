package com.calcaddpayschoolbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicNorms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    BigDecimal basicNormValue;
    @Column(nullable = false)
    LocalDate basicNormDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "basicNorms")
    private Set<Result> results;
}
