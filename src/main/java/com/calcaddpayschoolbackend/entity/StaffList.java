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
    @JoinColumn(name = "people_id", nullable = false)
    private People people;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;
    @Column(nullable = false)
    private BigDecimal salary;
    @Column(columnDefinition = "boolean default false")
    private boolean youngSpecial;
    //добавление значения по умолчанию в колонку
    @Column(columnDefinition = "boolean default false")
    private Boolean disabled;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staffList")
    private Set<TimeSheet> timeSheet;
}
