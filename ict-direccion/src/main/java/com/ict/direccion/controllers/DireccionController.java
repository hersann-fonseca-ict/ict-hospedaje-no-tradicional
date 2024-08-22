package com.ict.direccion.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.commons.entity.Canton;
import com.ict.commons.entity.Distrito;
import com.ict.commons.entity.Provincia;
import com.ict.direccion.services.IDireccionService;

import io.swagger.annotations.ApiOperation;

@RestController
public class DireccionController {

	@Autowired
	private IDireccionService direccionService;

	@ApiOperation(value = "Obtener por id", notes = "Obtiene un cantón por id", response = Canton.class)
	@GetMapping("/obtenerCantonPorId")
	public ResponseEntity<List<Canton>> obtenerPorId(Long id) {
		try {
			return ResponseEntity.ok(this.direccionService.obtenerCantonPorId(id));

		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<Canton>) null);
		}
	}

	@ApiOperation(value = "Obtener por id", notes = "Obtiene un distrito por id", response = Distrito.class)
	@GetMapping("/obtenerDistritoPorId")
	public ResponseEntity<List<Distrito>> obtenerDistritoPorId(Long id) {
		try {
			return ResponseEntity.ok(this.direccionService.obtenerDistritosPorId(id));

		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<Distrito>) null);
		}
	}

	@ApiOperation(value = "Obtener", notes = "Obtiene las provincias")
	@GetMapping("/obtener")
	public ResponseEntity<List<Provincia>> obtener() {
		try {
			return ResponseEntity.ok(this.direccionService.obtenerProvincias());
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<Provincia>) null);
		}
	}

	@ApiOperation(value = "Obtener", notes = "Obtiene las provincias")
	@GetMapping("/obtenerNombreCanton")
	public ResponseEntity<String> obtenerNombreCanton(Long id) {
		try {
			return ResponseEntity.ok(this.direccionService.obtenerCantonNombre(id));
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	@ApiOperation(value = "Obtener", notes = "Obtiene las provincias")
	@GetMapping("/obtenerNombreDistrito")
	public ResponseEntity<String> obtenerNombreDistrito(Long id) {
		try {
			return ResponseEntity.ok(this.direccionService.obtenerDistritoNombre(id));
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

}
