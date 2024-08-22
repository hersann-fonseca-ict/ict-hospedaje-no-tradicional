package com.ict.oauth.fegins;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.commons.entity.RolSistemaOpcion;
import com.ict.commons.entity.UsuarioSistema;

@FeignClient(name="servicio-usuarios")
public interface IUsuarioFeign {
	
	@GetMapping("/obtenerPorNombreUsuario")
	public UsuarioSistema obtenerPorNombreUsuario(@RequestParam String nombreUsuario);
	@GetMapping("/guardar")
	public UsuarioSistema guardar(@Valid @RequestBody UsuarioSistema usuario);
	
	
	@GetMapping("/obtenerOpcionesMenuPorIdUsuario")
	public List<RolSistemaOpcion> obtenerOpcionesMenuPorIdUsuario(@RequestParam Long Id);
}
