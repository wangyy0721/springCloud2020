package com.bytes.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.bytes.springcloud.alibaba.handler.CustomerBlockHandler;
import com.bytes.springcloud.entities.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RateLimitController {
	@GetMapping("/byResource")
	@SentinelResource(value = "byResource", blockHandler = "handleException")
	public CommonResult<?> byResource() {
		return new CommonResult<Payment>(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
	}

	public CommonResult<?> handleException(BlockException exception) {
		return new CommonResult<Payment>(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
	}

	@GetMapping("/rateLimit/byUrl")
	@SentinelResource(value = "byUrl")
	public CommonResult<?> byUrl() {
		return new CommonResult<Payment>(200, "按url限流测试OK", new Payment(2020L, "serial002"));
	}

	@GetMapping("/rateLimit/customerBlockHandler")
	@SentinelResource(value = "customerBlockHandler",
			blockHandlerClass = CustomerBlockHandler.class,
			blockHandler = "handleException2")
	public CommonResult<Payment> customerBlockHandler() {
		return new CommonResult<>(200, "按客戶自定义", new Payment(2020L, "serial003"));
	}

}