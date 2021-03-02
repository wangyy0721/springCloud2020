package com.bytes.springcloud.alibaba.controller;

import com.bytes.springcloud.alibaba.domain.CommonResult;
import com.bytes.springcloud.alibaba.domain.Order;
import com.bytes.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class OrderController {
	@Resource
	private OrderService orderService;


	@GetMapping("/order/create")
	public CommonResult create(Order order) {
		System.out.println("order = " + order);
		orderService.create(order);
		return new CommonResult(200, "订单创建成功");
	}
}

