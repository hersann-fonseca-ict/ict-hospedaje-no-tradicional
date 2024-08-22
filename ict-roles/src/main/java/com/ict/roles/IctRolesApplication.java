package com.ict.roles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EntityScan({"com.ict.commons.entity"})
@SpringBootApplication
public class IctRolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IctRolesApplication.class, args);
	}

}
