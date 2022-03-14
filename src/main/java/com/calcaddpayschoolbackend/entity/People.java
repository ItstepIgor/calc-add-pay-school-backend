package com.calcaddpayschoolbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@ToString(exclude = {"staffLists", "timeSheet"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class People extends AbstractEntity {

    @Column(nullable = false)
    private String surName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String patronymic;
    @Column(nullable = false)
    private String personnelNumber;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "people")
    private Set<StaffList> staffLists;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "people")
    private Set<TimeSheet> timeSheet;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "people")
    private Users users;
}
