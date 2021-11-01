package com.woo.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class logincontroller {


    @GetMapping("/login")

    public String getlogin(){
        return "login";
    }

}
