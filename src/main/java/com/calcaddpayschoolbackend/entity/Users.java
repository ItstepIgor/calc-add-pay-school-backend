package com.calcaddpayschoolbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "people_id")
    private People people;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
}
