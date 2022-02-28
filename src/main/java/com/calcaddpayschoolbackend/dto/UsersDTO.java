package com.calcaddpayschoolbackend.dto;

import com.calcaddpayschoolbackend.entity.Role;
import lombok.Data;

@Data
public class UsersDTO {
    private long id;
    private long peopleId;
    private String peopleSurAndFirstName;
    private  String password;
    private Role role;
}
