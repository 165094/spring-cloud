package com.test.auth.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResultEntity implements Serializable {

    /**
     *  accessToken
     */
    private String accessToken;

    /**
     *  refreshToken
     */
    private String refreshToken;

    /**
     * 登录用户名
     */
    private String username;
}
