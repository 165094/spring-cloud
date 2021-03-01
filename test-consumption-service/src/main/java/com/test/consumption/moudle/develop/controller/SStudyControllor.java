package com.test.consumption.moudle.develop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sStudy")
public class SStudyControllor {

    @GetMapping("/hello")
    public String helloControllor(){
        return "Hello word";
    }
}
