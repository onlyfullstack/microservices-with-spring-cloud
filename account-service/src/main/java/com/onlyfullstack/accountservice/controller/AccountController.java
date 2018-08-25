package com.onlyfullstack.accountservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.onlyfullstack.accountservice.bean.Account;
import com.onlyfullstack.accountservice.bean.Customer;
import com.onlyfullstack.accountservice.service.CustomerServiceProxy;

@RestController
public class AccountController {
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	private List<Account> listOfAccounts = new ArrayList<>(5);
	
/*	@Autowired // Part 2
	RestTemplate restTemplate;*/
	
	@Autowired
	CustomerServiceProxy customerServiceProxy;
	
	public AccountController(){
		 Account account1 = new Account(new Long(1), null, new Double(1111.50));
		 Account account2 = new Account(new Long(2), null, new Double(1112.50));
		 Account account3 = new Account(new Long(3), null, new Double(1113.50));
		 this.listOfAccounts.add(account1);
		 this.listOfAccounts.add(account2);
		 this.listOfAccounts.add(account3);
	}
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts(){
		return this.listOfAccounts;
	}
	
	@GetMapping("/accounts/{account_id}")
	@HystrixCommand(fallbackMethod="getAccountDetails_fallback")
	public Account getAccountDetails(@PathVariable Long account_id) {
		logger.debug("*** Entered in getAccountDetails with account_id: {}",account_id);
		Account account = this.listOfAccounts.stream().filter(acc -> acc.getId().equals(account_id)).findAny().orElse(null);
		/*Map<String, Long> params = new HashMap<>(); // Part 2 Changes
	    params.put("customer_id", account.getId());
		Customer customer = restTemplate.getForObject("http://localhost:8081/customers/{customer_id}" , Customer.class, params);*/

		Customer customer = customerServiceProxy.getCustomerDetails(account_id); // Part 3 Changes
		account.setCustomer(customer);
		logger.debug("*** Exiting from getAccountDetails with account: {}",account);
		return account;
	}

	
	public Account getAccountDetails_fallback(@PathVariable Long account_id) {
		logger.debug("*** Entered in getAccountDetails_fallback with account_id: {}",account_id);
		Account account = getDefaultAccount();
		return account;
	}

	private Account getDefaultAccount() {
		return new Account(new Long(100), null, new Double(0));
	}

}
