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
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String positionName;
    @Column(nullable = false)
    private String sorting;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private Set<StaffList> staffLists;
}
