package com.ict.solicitudes.controllers;

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
import com.ict.commons.entity.Solicitud;
import com.ict.solicitudes.models.SolicitudesPaginados;
import com.ict.solicitudes.repositories.ISolicitudRepositoryTabla;
import com.ict.solicitudes.services.ISolicitudService;

import io.swagger.annotations.ApiOperation;

@RestController
public class SolicitudController {

	@Autowired
	private ISolicitudService servicio;
	@Autowired
	private ISolicitudRepositoryTabla repository;

	@ApiOperation(value = "Guardar", notes = "Guarda una nueva solicitud", response = Solicitud.class)
	@PostMapping("/guardar")
	public ResponseEntity<Solicitud> guardar(@RequestBody Solicitud entidad) {

		try {
			return ResponseEntity.ok(this.servicio.guardar(entidad));
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Solicitud) null);
		}
	}

	@ApiOperation(value = "Obtener por id", notes = "Obtiene una Solicitud por id")
	@GetMapping("/obtenerPorId")
	public ResponseEntity<Solicitud> obtenerPorId(Long id) {
		try {
			Solicitud entidad = this.servicio.obtenerPorId(id);
			if (entidad == null) {
				return new ResponseEntity<Solicitud>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(entidad);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Solicitud) null);
		}
	}

	@ApiOperation(value = "ObtenerTest", notes = "Obtiene una solicitud")
	@GetMapping("/test")
	public ResponseEntity<Solicitud> example(Long id) {
		return ResponseEntity.ok(new Solicitud());
	}

	@ApiOperation(value = "Obtener", notes = "Obtiene una solicitud")
	@GetMapping("/obtener")
	public ResponseEntity<List<Solicitud>> obtener() {
		try {
			return ResponseEntity.ok(this.servicio.obtener());
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<Solicitud>) null);
		}
	}

	@ApiOperation(value = "Obtener Paginados", notes = "Obtiene las solicitudes paginadas")
	@GetMapping("/obtenerPaginados")
	public ResponseEntity<SolicitudesPaginados> obtenerPaginados(int pagina, int tamano, String columna) {
		try {
			SolicitudesPaginados entidad = this.servicio.obtenerPaginado(pagina, tamano, columna);
			if (entidad == null) {
				return new ResponseEntity<SolicitudesPaginados>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(entidad);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	@ApiOperation(value = "obtenerTabla", notes = "Obtiene solicitudes")
	@PostMapping("/obtenerTabla")
	public DataTablesOutput<Solicitud> getTabla(@Valid @RequestBody DataTablesInput input) {
		return repository.findAll(input);
	}

	@GetMapping("/obtenerProxId")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> obtenerProxId() {
		try {
			return ResponseEntity.ok(this.servicio.obtenerProxId());
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

}
