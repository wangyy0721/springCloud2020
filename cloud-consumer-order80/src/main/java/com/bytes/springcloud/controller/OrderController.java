package com.bytes.springcloud.controller;

import com.bytes.springcloud.entities.CommonResult;
import com.bytes.springcloud.entities.Payment;
import com.bytes.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

	public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

	@Autowired
	RestTemplate restTemplate;

	@Resource
	private LoadBalancer loadBalancer;

	@Resource
	private DiscoveryClient discoveryClient;

	@PostMapping("/payment/create")
	public CommonResult<?> create(@RequestBody Payment payment) {
		System.out.println("payment = " + payment);
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
	}

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
		ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
		if (entity.getStatusCode().is2xxSuccessful()) {
			return entity.getBody();
		}
		return new CommonResult<Payment>(404, "不存在id为" + id + "的信息", null);
	}

	@GetMapping(value = "/consumer/payment/lb")
	public CommonResult<Payment> getPaymentLB() {
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		if (instances == null || instances.size() <= 0) {
			return null;
		}
		ServiceInstance serviceInstance = loadBalancer.instances(instances);
		URI uri = serviceInstance.getUri();
		//return restTemplate.getForObject(uri+"/payment/lb",String.class);
		return new CommonResult<Payment>(404, uri.toString(), null);//这里直接返回一个错误信息，因为CLOUD-PAYMENT-SERVICE端，懒得写："/payment/lb"
	}

	// ====================> zipkin+sleuth
	@GetMapping("/consumer/payment/zipkin")
	public String paymentZipkin() {
		return restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
	}
}
