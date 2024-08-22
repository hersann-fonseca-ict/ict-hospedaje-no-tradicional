package com.ict.perfiles.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "servicio-solicitudes")
public interface ISolicitudFeign {

	@GetMapping("/obtenerProxId")
	public Long obtenerProxId();
}
