package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(exclude = {"addPayResults", "percentSalaryResult"})
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSheet extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private People people;
    @Column(nullable = false)
    private int actualDaysWorked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calc_settings_id")
    private CalcSettings calcSettings;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timeSheets")
    private Set<AddPayResult> addPayResults;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timeSheets")
    private Set<PercentSalaryResult> percentSalaryResult;
}
