package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(exclude = {"timeSheet", "addPayFunds"})
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalcSettings extends AbstractEntity {

    @Column(nullable = false)
    private LocalDate calcDate;
    @Column(nullable = false)
    private int workingDays;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calcSettings")
    private Set<TimeSheet> timeSheet;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calcSettings")
    private Set<AddPayFund> addPayFunds;
}
