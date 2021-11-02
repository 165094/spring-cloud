package com.test.consumption.moudle.develop.entity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public School school() {
        School school = new School();
        school.setAddr("");
        school.setUser(user());   //这里调用student()新建一个对象给school
        return school;
    }

    //生成student实例的方法
    @Bean
    public SUser user() {
        SUser user = new SUser();
        user.setUserName("xiaoming");
        user.setSex("男");
        return user;
    }
}
