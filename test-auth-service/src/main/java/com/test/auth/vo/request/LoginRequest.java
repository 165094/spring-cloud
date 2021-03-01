package com.test.auth.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest  {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空！")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空！")
    private String password;

    /**
     * 客户端ID
     */
    @NotBlank(message = "客户端ID不能为空！")
    private String clientId;

    /**
     * 客户端密钥
     */
    @NotBlank(message = "客户端密钥不能为空！")
    private String clientSecret;

}
