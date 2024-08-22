package com.ict.usuarios.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "servicio-notificaciones")
public interface INotificacionesFeign {

	@GetMapping("/enviarCorreo")
	public void enviarCorreo(@RequestParam String destino, @RequestParam String asunto, @RequestParam String cuerpo);

	@GetMapping("/enviarCorreoParam") 
	public void enviarCorreoParam(@RequestParam String destino, @RequestParam String asunto, @RequestParam String cuerpo,
			@RequestParam String param);
}
