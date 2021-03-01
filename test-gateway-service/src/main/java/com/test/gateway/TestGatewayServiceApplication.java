package com.test.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TestGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestGatewayServiceApplication.class, args);
    }

}
