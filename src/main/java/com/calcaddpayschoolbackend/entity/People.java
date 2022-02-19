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
    @Column(nullable = false)
    private boolean youngSpecial;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "people")
    private Set<StaffList> staffLists;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "people")
    private Set<TimeSheet> timeSheet;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "people")
    private Users users;
}
