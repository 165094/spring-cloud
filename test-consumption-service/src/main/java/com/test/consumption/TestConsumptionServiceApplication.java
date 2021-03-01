package com.test.consumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TestConsumptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestConsumptionServiceApplication.class, args);
	}

}

