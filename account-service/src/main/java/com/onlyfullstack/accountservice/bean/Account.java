package com.onlyfullstack.accountservice.bean;

public class Account {
	
	private Long id;
	private Customer customer;
	private Double balance;
	
	public Account(Long id, Customer customer, Double balance) {
		super();
		this.id = id;
		this.balance = balance;
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customer=" + customer + ", balance=" + balance + "]";
	}
	
}
