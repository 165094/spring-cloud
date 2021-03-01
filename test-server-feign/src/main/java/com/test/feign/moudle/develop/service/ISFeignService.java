package com.test.feign.moudle.develop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("test-consumption-service")
@Service
public interface ISFeignService {

    @RequestMapping("/sStudy/hello")
    String helloFeign();
}
