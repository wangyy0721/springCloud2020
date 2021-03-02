package com.bytes.springcloud;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderMain80Hystrix {

	public static void main(String[] args) {
		SpringApplication.run(OrderMain80Hystrix.class,args);
	}
}

