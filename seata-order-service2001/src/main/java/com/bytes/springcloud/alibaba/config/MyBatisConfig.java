package com.bytes.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.bytes.springcloud.alibaba.dao"})
public class MyBatisConfig {

}

