package com.ict.perfiles.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping; 

@FeignClient(name="servicio-beneficios")
public interface IBeneficioFeign {
	
	@GetMapping("/obtenerProxId")
	public Long obtenerProxId();
}
