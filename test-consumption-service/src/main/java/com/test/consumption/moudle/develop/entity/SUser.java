package com.test.consumption.moudle.develop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("s_user")
public class SUser implements Serializable {

    private static final long serialVersionUID = 6931505667718744614L;

    @TableId
    private Integer id;

    private String userNo;

    private String userName;

    private String passWord;

    private String sex;

    private String mail;

    private String phone;

    private String lastLoginTime;

    private String createTime;
}
