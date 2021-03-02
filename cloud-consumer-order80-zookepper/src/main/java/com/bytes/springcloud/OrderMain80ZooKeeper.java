package com.bytes.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderMain80ZooKeeper {
	public static void main(String[] args) {
		SpringApplication.run(OrderMain80ZooKeeper.class,args);
	}
}


