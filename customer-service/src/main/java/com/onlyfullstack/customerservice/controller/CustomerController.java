package com.onlyfullstack.customerservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.onlyfullstack.customerservice.bean.Customer;

@RestController
//@ConfigurationProperties("application-config")
@RefreshScope
public class CustomerController {
	
	@Value("${eureka.client.service-url.default-zone:No Value}")
	private String eureka_url;
	
	@Autowired
	private Environment environment; //Part 3
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	private List<Customer> listOfCustomers = new ArrayList<>(5);
	
	public CustomerController(){
		 Customer customer1 = new Customer(new Long(1), "Saurabh Oza", "Pune");
		 Customer customer2 = new Customer(new Long(2), "Amit Sharma", "Delhi");
		 Customer customer3 = new Customer(new Long(3), "Vivek Sinha", "Mumbai");
		 this.listOfCustomers.add(customer1);
		 this.listOfCustomers.add(customer2);
		 this.listOfCustomers.add(customer3);
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		logger.debug("*** eureka_url : "+eureka_url);
		return this.listOfCustomers;
	}
	
	@GetMapping("/customers/{customer_id}")
	public Customer getCustomerDetails(@PathVariable Long customer_id) {
		logger.debug("*** Entered in getCustomerDetails with customer_id: {}",customer_id);
		Customer customer = this.listOfCustomers.stream().filter(cust -> cust.getId().equals(customer_id)).findAny().orElse(null);
		customer.setPort(Integer.parseInt(environment.getProperty("server.port"))); // Part 3
		logger.debug("*** Found Object : {}",customer.toString());
		logger.debug("*** Config value : "+environment.getProperty("minimum-connection"));
		return customer;
	}

}
