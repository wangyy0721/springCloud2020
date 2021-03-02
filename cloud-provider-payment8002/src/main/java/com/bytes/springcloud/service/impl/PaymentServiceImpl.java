package com.bytes.springcloud.service.impl;

import com.bytes.springcloud.service.PaymentService;
import com.bytes.springcloud.dao.PaymentDao;
import com.bytes.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private PaymentDao paymentDao;

	public int create(Payment payment){
		return paymentDao.create(payment);
	}

	public Payment getPaymentById( Long id){

		return paymentDao.getPaymentById(id);

	}
}
