package com.onlyfullstack.accountservice.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onlyfullstack.accountservice.bean.Customer;

// @FeignClient(url="localhost:8081" , name="customer-service")
@FeignClient(name="customer-service")
@RibbonClient(name="customer-service")
public interface CustomerServiceProxy {

	@GetMapping("/customers")
	public List<Customer> getAllCustomers();
	
	@GetMapping("/customers/{customer_id}")
	public Customer getCustomerDetails(@PathVariable("customer_id") Long customer_id);
}

