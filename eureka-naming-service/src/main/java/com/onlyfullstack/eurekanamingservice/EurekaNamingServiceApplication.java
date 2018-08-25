package com.onlyfullstack.eurekanamingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaNamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaNamingServiceApplication.class, args);
	}
}
