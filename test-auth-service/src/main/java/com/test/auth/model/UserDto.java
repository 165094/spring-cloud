package com.test.auth.model;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String userName;

    private String passWord;

    private String phone;

    private String sex;
}
