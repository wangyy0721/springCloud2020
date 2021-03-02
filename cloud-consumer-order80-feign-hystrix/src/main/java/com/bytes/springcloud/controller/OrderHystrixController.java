package com.bytes.springcloud.controller;

import com.bytes.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globTimeOutFallbackMethod")
public class OrderHystrixController {

	@Resource
	private PaymentHystrixService paymentHystrixService;


	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
	@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")  //2秒钟以内就是正常的业务逻辑
	})
	public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
		System.out.println("id = " + id);
		String result = paymentHystrixService.paymentInfo_TimeOut(id);
		log.info("*******result:" + result);
		return result;
	}

	@GetMapping("/consumer/payment/hystrix/timeout2/{id}")
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
	})
	public String paymentInfo_TimeOut2(@PathVariable("id") Integer id) {
		System.out.println("id = " + id);
		String result = paymentHystrixService.paymentInfo_TimeOut(id);
		log.info("*******result:" + result);
		return result;
	}

	//兜底方法
	public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
		return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
	}

	public String globTimeOutFallbackMethod() {
		return "服务器繁忙，请稍后重试";
	}

}


