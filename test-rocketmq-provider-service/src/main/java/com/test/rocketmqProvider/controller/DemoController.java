package com.test.rocketmqProvider.controller;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

//@RestController
public class DemoController {

    @Autowired
    private DefaultMQProducer producer;

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "0/5 * *  * * ?")
    private void sendMsgToMq() {
        String str = "发送测试消息";
        Message msg;
        try {
            msg = new Message("test-demo"
                    , "111"
                    , UUID.randomUUID().toString()
                    , str.getBytes("utf-8"));
            SendResult result = producer.send(msg);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                System.out.println("消息发送成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
