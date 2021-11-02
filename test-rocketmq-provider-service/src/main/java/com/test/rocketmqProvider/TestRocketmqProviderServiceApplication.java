package com.test.rocketmqProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestRocketmqProviderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestRocketmqProviderServiceApplication.class, args);
	}

}
