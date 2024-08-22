package com.ict.secuencia.controllers;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ict.commons.entity.Secuencia;
import com.ict.secuencia.services.ISecuenciaService;

@RestController
public class SecuenciaController {

	@Autowired
	private ISecuenciaService service;

	@PostMapping("/obtenerProxSec")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> obtenerProxSec(@Valid @RequestBody Secuencia secuencia) {
		try {
			Secuencia sec = this.service.guardar(secuencia);
			String ceros = StringUtils.leftPad(sec.getSecuencia().toString(), 6, "0");
			return ResponseEntity.ok(sec.getId() + ceros);

		} catch (Exception ex) {
			System.out.print(ex.toString());
			if (ex.toString().contains("ConstraintViolationException")) {
				return ResponseEntity.status(409).header("mensaje", "Ya existe una secuencia").body((String) null);
			}
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((String) null);
		}
	}

}
