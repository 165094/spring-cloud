package com.test.auth.service;

import com.test.auth.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("consumption-service")
public interface IAuthService {

    @GetMapping("/user/{name}")
    UserDto queryUserByName(@PathVariable(value = "name")String name);
}
