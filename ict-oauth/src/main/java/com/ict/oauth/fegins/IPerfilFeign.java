package com.ict.oauth.fegins;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.commons.entity.Perfil;

@FeignClient(name="servicio-perfiles")
public interface IPerfilFeign {

	@GetMapping("/obtenerPorNombreUsuario")
	public Perfil obtenerPorNombreUsuario(@RequestParam String nombreUsuario);
	
}
