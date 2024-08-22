package com.ict.alojamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients; 

@EnableFeignClients
@EnableEurekaClient
@EntityScan({"com.ict.commons.entity"})
@SpringBootApplication
public class IctAlojamientoApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(IctAlojamientoApplication.class, args);
	}

}
