package com.woo.project.controller;


import com.woo.project.model.User;
import com.woo.project.repository.UserRepository;
import com.woo.project.service.UserService;
import com.woo.project.validator.BoardValidator;
import com.woo.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller



public class usercontroller {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/user")
    public String getuser(Model model, @RequestParam(required = false) Long id)
    {   if(id==null){
        model.addAttribute("user",new User());
    }

        return "user";
    }

    @PostMapping("/user")
    public  String postuser(@Valid User user, BindingResult bindingResult){
        userValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors()){
            return "/user";
        }
        userService.save(user);
        return  "redirect:login";
    }

    
}
