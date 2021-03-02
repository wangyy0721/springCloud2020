package com.bytes.springcloud.config;

import com.bytes.myrule.MySelfRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

	@Bean
//	@LoadBalanced//负载均衡，这样就可以使用服务名称代替具体http url访问服务。
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
