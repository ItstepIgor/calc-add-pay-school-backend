package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.entity.Role;
import com.calcaddpayschoolbackend.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/role")
@RequiredArgsConstructor
public class RoleController {

    private final UsersService usersService;

    @GetMapping("/get")
    public Role getRole (Principal principal){
        return usersService.findByUserLogin(principal.getName()).getRole();
    }
}
