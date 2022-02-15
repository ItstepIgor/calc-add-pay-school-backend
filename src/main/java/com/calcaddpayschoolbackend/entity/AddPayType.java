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
public class AddPayType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String addPayTypeName;
}
