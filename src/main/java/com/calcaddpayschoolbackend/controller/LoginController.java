package com.calcaddpayschoolbackend.controller;

import com.calcaddpayschoolbackend.entity.Role;
import com.calcaddpayschoolbackend.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

private final  UsersService  usersService;


    @GetMapping("/login")
    public String admin(Authentication authentication) {
        System.out.println((UserDetails)authentication.getPrincipal());
        return "Admin";
    }

    @GetMapping("/test")
    public Role test (Principal principal){
        return usersService.findByUserLogin(principal.getName()).getRole();
    }


}
