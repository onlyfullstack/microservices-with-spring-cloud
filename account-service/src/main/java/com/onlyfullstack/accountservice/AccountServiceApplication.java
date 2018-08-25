package com.onlyfullstack.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

//import com.onlyfullstack.zuulgatewayservice.AlwaysSampler;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
		
	}
	
/*	 @Bean //Part 2
	 public RestTemplate restTemplate() {
	  return new RestTemplate();
	 }*/
	
	/*@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}*/
}
