package com.bytes.springcloud.service.impl;

import com.bytes.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

import javax.annotation.Resource;

import org.springframework.cloud.stream.messaging.Source;

import java.util.UUID;


@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {
	@Resource
	private MessageChannel output; // 消息发送管道

	@Override
	public String send() {
		String serial = UUID.randomUUID().toString();
		return this.send(serial);
	}

	@Override
	public String send(String msg) {
		System.out.println("*****serial: " + msg);
		return output.send(MessageBuilder.withPayload(msg).build()) + "";
	}

}


