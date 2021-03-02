package com.bytes.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8004Zookeeper {
	public static void main(String[] args) {
		SpringApplication.run(PaymentMain8004Zookeeper.class, args);
	}
}


