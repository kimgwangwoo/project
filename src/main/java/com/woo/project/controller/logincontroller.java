package com.woo.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logincontroller {
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("menu")
    public String menu(){
        return "menu";
    }
}
