package com.calcaddpayschoolbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position extends AbstractEntity {

    @Column(nullable = false)
    private String positionName;
    @Column(nullable = false)
    private String sorting;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private Set<StaffList> staffLists;
}
