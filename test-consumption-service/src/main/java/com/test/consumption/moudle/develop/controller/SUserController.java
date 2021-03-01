package com.test.consumption.moudle.develop.controller;

import com.test.aop.develop.config.ConfigService;
import com.test.consumption.moudle.develop.entity.SUser;
import com.test.consumption.moudle.develop.service.ISUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/user")
public class SUserController {
    private static Logger logger = Logger.getLogger(SUserController.class);

    @Autowired
    private ISUserService userService;

    private ConfigService configService;

    @GetMapping(value = "/{name}")
    public SUser detail(@PathVariable @NotBlank(message = "用户名称不能为空！") String name){
        SUser sUser = userService.selectUserByName(name);
//        if (sUser == null){
//            return ResultVO.error("404", "找不到对应的记录");
//        }
//        return ResultVO.success("查询成功", sUser);
        return sUser;
    }

    @RequestMapping(value = "/test")
    public String test(){
//        configService.demo();
        logger.info("访问成功");
        return "访问成功!";
    }
}
