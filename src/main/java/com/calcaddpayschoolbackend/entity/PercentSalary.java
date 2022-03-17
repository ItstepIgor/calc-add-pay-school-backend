package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(exclude = {"percentSalaryResult"})
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercentSalary extends AbstractEntity {

    @Column(nullable = false)
    private LocalDate percentStartDate;
    @Column(nullable = false)
    private int percentSalaryAll;
    @Column(nullable = false)
    private int percentSalaryForYoungSpecial;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "percentSalary")
    private Set<PercentSalaryResult> percentSalaryResult;
}
