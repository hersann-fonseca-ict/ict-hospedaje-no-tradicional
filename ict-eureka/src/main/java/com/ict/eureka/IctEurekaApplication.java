package com.ict.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class IctEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IctEurekaApplication.class, args);
	}

}
