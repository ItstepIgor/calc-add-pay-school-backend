package com.calcaddpayschoolbackend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {


    @GetMapping("/login")
    public String admin(Authentication authentication) {
        System.out.println((UserDetails)authentication.getPrincipal());
        return "Admin";
    }
}
