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
public class AddPay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long addPayTypeId;
    private String addPayCode;
    private String addPayName;
    private int maxPercent;
    private String description;
}
