package com.test.rabbit.consumer.develop.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitFanoutListener {

    @RabbitHandler
    @RabbitListener(queues = "myDirectQueue")
    public void process(String msg) {
        System.out.println(msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanoutQueueA")
    public void processA(String msg) {
        System.out.println("fanoutQueueA " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanoutQueueB")
    public void processB(String msg) {
        System.out.println("fanoutQueueB " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanoutQueueC")
    public void processC(String msg) {
        System.out.println("fanoutQueueC " + msg);
    }
}
