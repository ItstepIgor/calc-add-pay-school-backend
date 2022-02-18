package com.calcaddpayschoolbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalcSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate calcDate;
    @Column(nullable = false)
    private int workingDays;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calcSettings")
    private Set<TimeSheet> timeSheet;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calcSettings")
    private Set<AddPayFund> addPayFunds;
}
