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
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String surName;
    private String firstName;
    private String patronymic;
    private String personnelNumber;
    private String phoneNumber;
    private String address;
    private boolean youngSpecial;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "people")
    private Set<StaffList> staffLists;
}
