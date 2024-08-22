package com.ict.notificaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.notificaciones.services.INotificacionesService;

@RestController
public class NotificacionController {
	
	@Autowired
	private INotificacionesService service;
	
	@GetMapping("/enviarCorreo")
	public void enviarCorreo(@RequestParam String destino, @RequestParam String asunto, @RequestParam String cuerpo) {
		this.service.enviarCorreo(destino, asunto, cuerpo);
	}
	
	@GetMapping("/enviarCorreoParam")
	public void enviarCorreo(@RequestParam String destino, @RequestParam String asunto, @RequestParam String cuerpo, @RequestParam String param) {
		this.service.enviarCorreoParam(destino, asunto, cuerpo,param);
	}
	
}
