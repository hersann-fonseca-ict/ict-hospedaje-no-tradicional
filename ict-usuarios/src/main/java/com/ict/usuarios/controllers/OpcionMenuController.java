package com.ict.usuarios.controllers;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;


import com.ict.commons.entity.RolSistemaOpcion;

import com.ict.usuarios.services.IOpcionMenuService;



@RestController
public class OpcionMenuController {



	@Autowired
	private IOpcionMenuService service;
	

	@GetMapping("/obtenerOpcionesMenuPorIdUsuario")
	public ResponseEntity<List<RolSistemaOpcion>> obtenerOpcionesMenuPorIdUsuario(@RequestParam Long Id)
	{
		try {
				return ResponseEntity.ok(this.service.obtenerOpcionesPorIdUsuario(Id));
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<RolSistemaOpcion>) null);
		}
	}
}













	


