package com.ict.notificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EntityScan({"com.ict.commons.entity"})
@SpringBootApplication
public class IctNotificacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IctNotificacionesApplication.class, args);
	}

}
