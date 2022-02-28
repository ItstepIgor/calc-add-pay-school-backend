package com.calcaddpayschoolbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
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
    @Column(nullable = true)
    private boolean disabled;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staffList")
    private Set<Result> results;
}
