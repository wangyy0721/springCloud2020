package com.bytes.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bytes.springcloud.entities.CommonResult;
import com.bytes.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

public class CustomerBlockHandler {
	public static CommonResult handleException1(BlockException exception) {
		return new CommonResult(2020, "自定义限流处理信息....CustomerBlockHandler-1");

	}
	public static CommonResult handleException2(BlockException exception) {
		return new CommonResult(2020, "自定义限流处理信息....CustomerBlockHandler-2");

	}
}
