package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@ToString(exclude = {"addPayResults", "percentSalaryResult"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffList extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private People people;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;
    @Column(nullable = false)
    private BigDecimal salary;
    @Column(columnDefinition = "boolean default false")
    private boolean youngSpecial;
    //добавление значения по умолчанию в колонку
    @Column(columnDefinition = "boolean default false")
    private Boolean disabled;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staffList")
    private Set<AddPayResult> addPayResults;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staffList")
    private Set<PercentSalaryResult> percentSalaryResult;
}
