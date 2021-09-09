package com.test.rabbit.consumer.develop.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitTopicListener {

    @RabbitHandler
    @RabbitListener(queues = "myTopicQueue_01")
    public void process_01(String msg) {
        System.out.println("myTopicQueue_01 " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "myTopicQueue_02")
    public void process_02(String msg) {
        System.out.println("myTopicQueue_02 " + msg);
    }
}
