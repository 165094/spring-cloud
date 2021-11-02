package com.test.rocketmqProvider.controller;

import com.test.rocketmqProvider.dto.OrderDTO;
import com.test.rocketmqProvider.service.OrderService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/create_order")
    public void createOrder(@RequestBody OrderDTO order) throws MQClientException {
        logger.info("接收到订单数据：{}",order.getOrderNo());
        orderService.createOrder(order);
    }
}
