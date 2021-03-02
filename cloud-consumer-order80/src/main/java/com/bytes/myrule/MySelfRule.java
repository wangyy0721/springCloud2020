package com.bytes.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

	public MySelfRule() {
		System.out.println("构造MySelfRule");
	}
	@Bean
	public IRule myRule() {
		System.out.println("创建IRule");
		return new RandomRule();//定义为随机
	}
}


