package com.bytes.springcloud.filter;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("*********come in MyLogGateWayFilter: "+new Date());
		String username = exchange.getRequest().getQueryParams().getFirst("username");
		if(StringUtils.isEmpty(username)){

			log.info("*****用户名为Null 非法用户,(┬＿┬)");

			ServerHttpResponse response = exchange.getResponse();

			JSONObject message = new JSONObject();
			message.put("status", -1);
			message.put("msg", "请求中必须存在username参数");
			message.put("filter", "global-filter:com.bytes.springcloud.filter.MyLogGateWayFilter");
			byte[] bits = message.toJSONString(4).getBytes(StandardCharsets.UTF_8);
			DataBuffer buffer = response.bufferFactory().wrap(bits);

			response.setStatusCode(HttpStatus.UNAUTHORIZED);

			//指定编码，否则在浏览器中会中文乱码
			response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");

			return response.writeWith(Mono.just(buffer));
//			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}


