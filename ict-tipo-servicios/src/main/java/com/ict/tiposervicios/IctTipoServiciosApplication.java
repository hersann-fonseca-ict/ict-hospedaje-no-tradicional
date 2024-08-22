package com.ict.tiposervicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@EntityScan({"com.ict.commons.entity"})
@SpringBootApplication
public class IctTipoServiciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(IctTipoServiciosApplication.class, args);
	}

}
