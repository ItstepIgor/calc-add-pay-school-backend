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
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private People people;
    @Column(nullable = false)
    private int actualDaysWorked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calcsettings_id")
    private CalcSettings calcSettings;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "staffList")
    private Set<Result> results;
}
