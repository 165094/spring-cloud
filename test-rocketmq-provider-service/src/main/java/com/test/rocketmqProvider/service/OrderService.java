package com.test.rocketmqProvider.service;

import com.test.rocketmqProvider.dto.OrderDTO;
import org.apache.rocketmq.client.exception.MQClientException;

public interface OrderService {

    void createOrder(OrderDTO order, String transactionId);

    void createOrder(OrderDTO order) throws MQClientException;
}
