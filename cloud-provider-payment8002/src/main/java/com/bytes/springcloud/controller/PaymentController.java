package com.bytes.springcloud.controller;

import com.bytes.springcloud.entities.CommonResult;
import com.bytes.springcloud.entities.Payment;
import com.bytes.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

	@Resource//@Resource:java自带的依赖注入
	private PaymentService paymentService;

	@Value("${server.port}")
	private String serverPort;

	@Resource
	private DiscoveryClient discoveryClient;

	@GetMapping(value = "/payment/discovery")
	public Object discovery() {
		List<String> services = discoveryClient.getServices();
		log.info("***** services:" + Arrays.toString(services.toArray()));
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		for (ServiceInstance instance : instances) {
			log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
		}
		return this.discoveryClient;
	}
	@PostMapping(value = "/payment/create")
	public CommonResult<Integer> create(@RequestBody  Payment payment){
		System.out.println("payment = " + payment);
		if (payment.getSerial()==null) {
			return new CommonResult<Integer>(444,"serverPort:"+serverPort+"|->插入数据库失败",null);
		}
		int result = paymentService.create(payment);
		log.info("*****插入结果："+result);
		if (result>0){  //插入成功
			return new CommonResult<>(200,"serverPort:"+serverPort+"|->插入数据库成功",result);
		}else {
			return new CommonResult<Integer>(444,"serverPort:"+serverPort+"|->插入数据库失败",null);
		}
	}

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
		Payment payment = paymentService.getPaymentById(id);
		log.info("*****查询结果："+payment);
		if (payment!=null){  //说明有数据，能查询成功
			return new CommonResult<>(200,"serverPort:"+serverPort+"|->查询成功",payment);
		}else {
			return new CommonResult<Payment>(444,"serverPort:"+serverPort+"|->没有对应记录，查询ID："+id,null);
		}
	}
	@GetMapping(value = "/payment/get")
	public CommonResult<Payment> getPaymentById2(@Param("id") Long id){
		return getPaymentById(id);
	}

	@GetMapping(value = "/payment/feign/timeout")
	public String paymentFeignTimeout(){
		try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
		return serverPort;
	}

	@GetMapping(value = "/payment/lb")
	public String lb(){
		return serverPort;
	}

	@GetMapping(value = "/payment/lb-no-route")
	public String lb_no_route(){
		return serverPort;
	}
}


