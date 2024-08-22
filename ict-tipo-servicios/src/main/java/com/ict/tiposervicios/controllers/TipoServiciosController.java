package com.ict.tiposervicios.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ict.commons.entity.TipoServicio;
import com.ict.tiposervicios.services.ITipoServicioService;

import io.swagger.annotations.ApiOperation;

@RestController
public class TipoServiciosController {
	
	@Autowired
	private ITipoServicioService tipoServicioService;
	
	@ApiOperation(value = "Guardar", notes = "Guarda un nuevo tipo de servicio", response =  TipoServicio.class)
	@PostMapping("/guardar")
	public ResponseEntity<TipoServicio> guardar(@RequestBody TipoServicio tipoServicio){
		try {
			return ResponseEntity
					.ok(this.tipoServicioService.guardar(tipoServicio));
		}catch(Exception ex) {
			System.out.print(ex.toString());
	        if(ex.toString().contains("ConstraintViolationException")) {
	        	return ResponseEntity.status(409).header("mensaje", "Existe un tipo de servicio con ese nombre")
						.body((TipoServicio)null);
	        }
	        
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((TipoServicio)null);
		}
	}
	
	@ApiOperation(value = "Obtener por id", notes = "Obtiene un tipo de servicio por id", response =  TipoServicio.class)
	@GetMapping("/obtenerPorId")
	public ResponseEntity<TipoServicio> obtenerPorId(Long id){
		try {
			TipoServicio tipoServicio = this.tipoServicioService.obtenerPorId(id);
			if(tipoServicio == null) {
				return new ResponseEntity<TipoServicio>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity
					.ok(tipoServicio);
		}catch(Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((TipoServicio)null);
		}
	}
	
	@ApiOperation(value = "Obtener", notes = "Obtiene un tipo de servicio por id")
	@GetMapping("/obtener")
	public ResponseEntity<List<TipoServicio>> obtener(){
		try {
			return ResponseEntity
					.ok(this.tipoServicioService.obtener());
		}catch(Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((List<TipoServicio>)null);
		}
	}
	
	@ApiOperation(value = "init", notes = "Inicializa los datos")
	@GetMapping("/init")
	public ResponseEntity<List<TipoServicio>> init(){
		if(this.tipoServicioService.obtener().isEmpty()) {
			this.tipoServicioService.guardar( new TipoServicio(1L, "Servicio de hospedaje no tradicional", true, new Date(), "HNT") );
			this.tipoServicioService.guardar( new TipoServicio(2L, "Empresa intermediaria o comercializadora", false, new Date(), "EI") );;
		}
		return ResponseEntity
				.ok(this.tipoServicioService.obtener());
	}
	
}
