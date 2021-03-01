package com.test.feign.moudle.develop.Controllor;

import com.test.feign.moudle.develop.service.ISFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sFeign")
public class SFeignControllor {

    @Autowired
    private ISFeignService service;

    @GetMapping("/testFeign")
    public String testFeign(){
        return service.helloFeign();
    }
}
