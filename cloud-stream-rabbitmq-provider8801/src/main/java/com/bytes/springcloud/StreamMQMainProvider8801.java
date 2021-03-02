package com.bytes.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamMQMainProvider8801 {
	public static void main(String[] args) {
		SpringApplication.run(StreamMQMainProvider8801.class, args);
	}
}


