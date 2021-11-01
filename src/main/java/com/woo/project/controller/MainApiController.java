package com.woo.project.controller;

import com.woo.project.model.Board;
import com.woo.project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;
import java.util.List;

@RestController
public class MainApiController {
    @Autowired
    private  BoardRepository repository;

    @GetMapping("/board")
    List<Board> all(@RequestParam(required = false,defaultValue = "")String title,
    @RequestParam(required = false,defaultValue = "")String contents){
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(contents)){
            return repository.findAll();
        }else{
            return repository.findByTitle(title);
        }

    }
}
