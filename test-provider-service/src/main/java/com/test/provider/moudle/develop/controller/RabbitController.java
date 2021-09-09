package com.test.provider.moudle.develop.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/send")
    public String send() {

        String msg = "hello";
        rabbitTemplate.convertAndSend("myDirectExchange", "my.direct.routing", msg);

        return "success";
    }

    @RequestMapping("/sendByFanout")
    public String sendByFanout() {

        String msg = "hello fanout";
        rabbitTemplate.convertAndSend("fanoutExchange", null, msg);

        return "success";
    }

    @RequestMapping("/sendByTopic")
    public String sendByTopic() {

        String msg = "hello topic";
        rabbitTemplate.convertAndSend("myTopicExchange", "topic.01", msg + " topic.01");
        rabbitTemplate.convertAndSend("myTopicExchange", "topic.xxx", msg + " topic.xxx");

        return "success";
    }
}
