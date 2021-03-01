package com.test.consumption.moudle.develop.kafka;

import com.alibaba.fastjson.JSONObject;
import com.test.consumption.moudle.develop.entity.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class Product {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/send")
    public void send(){
        SUser user = new SUser();
        user.setMail("1650943282@qq.com");
        user.setPhone("154984563");
        kafkaTemplate.send("firstTopic", JSONObject.toJSONString(user));
    }
}
