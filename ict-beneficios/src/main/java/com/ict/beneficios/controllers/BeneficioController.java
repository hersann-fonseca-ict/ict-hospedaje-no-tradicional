package com.ict.beneficios.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
import com.ict.beneficios.repositories.IBeneficiosRepositoryTable;
import com.ict.beneficios.services.IBeneficiosService;
import com.ict.commons.entity.Beneficio; 

import io.swagger.annotations.ApiOperation;

@RestController
public class BeneficioController {

	@Autowired
	private IBeneficiosService servicio;
	@Autowired
	private IBeneficiosRepositoryTable repository;

	@ApiOperation(value = "Guardar", notes = "Guarda un nuevo beneficio", response = Beneficio.class)
	@PostMapping("/guardar")
	public ResponseEntity<Beneficio> guardar(@RequestBody Beneficio entidad) {
		try {
			if (this.servicio.obtenerPorNombre(entidad) == null) {
				return ResponseEntity.ok(this.servicio.guardar(entidad));
			}
			return ResponseEntity.status(409).header("mensaje", "Existe un Beneficio con ese nombre")
					.body((Beneficio) null);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Beneficio) null);
		}
	}

	@ApiOperation(value = "Obtener por id", notes = "Obtiene un beneficio por id", response = Beneficio.class)
	@GetMapping("/obtenerPorId")
	public ResponseEntity<Beneficio> obtenerPorId(Long id) {
		try {
			Beneficio entidad = this.servicio.obtenerPorId(id);
			if (entidad == null) {
				return new ResponseEntity<Beneficio>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(entidad);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Beneficio) null);
		}
	}

	@ApiOperation(value = "obtenerTabla", notes = "Obtiene beneficios")
	@PostMapping("/obtenerTabla")
	public DataTablesOutput<Beneficio> getTabla(@Valid @RequestBody DataTablesInput input) {
		return repository.findAll(input);
	}

	@GetMapping("/obtener")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Beneficio>> obtener() {
		try {
			return ResponseEntity.ok(this.servicio.obtener());
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<Beneficio>) null);
		}
	}

	@GetMapping("/obtenerProxId")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> obtenerProxId() {
		try {
			return ResponseEntity.ok(this.servicio.obtenerProxId());			
		}catch(Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body(null);
		}
	}

}
