package com.bytes.springcloud.dao;

import com.bytes.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

	Payment getPaymentById(Long id);

	int create(Payment payment);
}

