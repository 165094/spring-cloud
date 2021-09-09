package com.test.provider.moudle.develop.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    // ----- 交换机 -----
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange", true, false);
    }

    // ----- 队列 -----
    @Bean
    public Queue fanoutQueueA() {
        return new Queue("fanoutQueueA", true);
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue("fanoutQueueB", true);
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue("fanoutQueueC", true);
    }

    // ----- 绑定 -----
    @Bean
    public Binding bindingFanoutA() {
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutB() {
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutC() {
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }

}
