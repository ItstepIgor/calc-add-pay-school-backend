package com.calcaddpayschoolbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping()
public class LoginController {


    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
}
