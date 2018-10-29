package com.byk.rong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RongThirdApplication {

	public static void main(String[] args) {
		SpringApplication.run(RongThirdApplication.class, args);
	}
}
