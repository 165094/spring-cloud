package com.test.consumption.moudle.develop.controller;

import com.test.consumption.moudle.develop.entity.SUser;
import com.test.consumption.moudle.develop.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    private School school;  //注入school

    @Autowired
    private SUser user;    //注入student

    @RequestMapping("/test")
    public String test() {
        if (school.getUser() == user) {   //判断是否是同一个对象
            return "同一个user对象";
        } else {
            return "不同的user对象";
        }
    }
}
