package com.calcaddpayschoolbackend.dto;

import com.calcaddpayschoolbackend.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private long peopleId;
    private  String password;
    private Role role;
}