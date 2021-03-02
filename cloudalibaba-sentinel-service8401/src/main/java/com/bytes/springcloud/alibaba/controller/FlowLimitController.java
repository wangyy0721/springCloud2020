package com.bytes.springcloud.alibaba.controller;


import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Log4j2
@RestController
public class FlowLimitController {
	@GetMapping("/testA")
	public String testA() {
		return "------testA";
	}

	@GetMapping("/testB")
	public String testB() {

		return "------testB";
	}

	@GetMapping("/testD")
	public String testD() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("testD 测试RT");
		return "------testD";
	}

	@GetMapping("/testE")
	public String testE() {
		log.info("testE 测试异常数");
		int age = 10 / 0;
		return "------testE 测试异常数";
	}

	@GetMapping("/testHotKey")
	@SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
	public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
	                         @RequestParam(value = "p2", required = false) String p2) {
		//int age = 10/0;
		return "------testHotKey: { p1 : "+p1+" , p2 : "+p2+" } ";
	}

	//兜底方法
	public String deal_testHotKey(String p1, String p2, BlockException exception) {
		return "------deal_testHotKey,o(╥﹏╥)o";
	}


}


