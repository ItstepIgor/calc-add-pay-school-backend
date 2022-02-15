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
    BigDecimal basicNormValue;
    LocalDate basicNormDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "basicNorms")
    private Set<Result> results;
}
