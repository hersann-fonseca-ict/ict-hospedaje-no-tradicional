package com.ict.localizacioninmueble.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.ict.commons.entity.Secuencia;


@FeignClient(name = "servicio-secuencia")
public interface ISecuenciaFeign {

	@PostMapping("/obtenerProxSec")
	public String obtenerProxSec(Secuencia secuencia);
}
