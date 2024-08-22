package com.ict.alojamiento.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ict.alojamiento.models.AlojamientoModel;
import com.ict.alojamiento.services.IAlojamientoService;
import com.ict.commons.entity.Alojamiento;
import com.ict.commons.models.ReporteGraficosModel;

@RestController
public class AlojamientoController {

	@Autowired
	private IAlojamientoService service;

	@PostMapping("/guardar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Alojamiento> guardar(@Valid @RequestBody Alojamiento entidad) {
		try {
			return ResponseEntity.ok(this.service.guardar(entidad));

		} catch (Exception ex) {
			System.out.print(ex.toString());
			if (ex.toString().contains("ConstraintViolationException")) {
				return ResponseEntity.status(409).header("mensaje", "Ya existe un alojamiento con ese nombre")
						.body((Alojamiento) null);
			}
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Alojamiento) null);
		}
	}

	@GetMapping("/listado")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Alojamiento>> listado() {
		try {
			return ResponseEntity.ok(this.service.listado());

		} catch (Exception ex) {
			System.out.print(ex.toString());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<Alojamiento>) null);
		}
	}

	@GetMapping("/listadoPorPadre")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Alojamiento>> listadoPorPadre(Long padre) {
		try {
			return ResponseEntity.ok(this.service.listadoPorPadre(padre));

		} catch (Exception ex) {
			System.out.print(ex.toString());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<Alojamiento>) null);
		}
	}
	
	@GetMapping("/listadoClase")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<AlojamientoModel>> listadoClase() {
		try {
			return ResponseEntity.ok(this.service.listadoClase());

		} catch (Exception ex) {
			System.out.print(ex.toString());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<AlojamientoModel>) null);
		}
	}
	
	@GetMapping("/obtenerGraficoAlojamiento")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ReporteGraficosModel>> obtenerDatosPorProvincia(){
		try {
			List<ReporteGraficosModel> reporte = new ArrayList<ReporteGraficosModel>();
			reporte = this.service.obtenerDatosGrafico();
			return ResponseEntity.ok(reporte);

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<ReporteGraficosModel>) null);
		}
	}
}
