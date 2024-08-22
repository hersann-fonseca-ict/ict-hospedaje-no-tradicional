package com.ict.localizacioninmueble.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ict.commons.entity.LocalizacionInmueble;
import com.ict.commons.models.ReporteGraficosModel;
import com.ict.localizacioninmueble.services.ILocalizacionInmuebleService;
import com.ict.localizacioninmueble.feigns.INotificacionesFeign;
import com.ict.localizacioninmueble.repositories.IProvinciaRepository;

@RestController
public class LocalizacionInmuebleController {

	@Autowired
	private ILocalizacionInmuebleService service;
	
	@Autowired
	private IProvinciaRepository repoProvincia;
	
	@Autowired
	private INotificacionesFeign notificaciones;

	@PostMapping("/guardar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LocalizacionInmueble> guardar(@Valid @RequestBody LocalizacionInmueble entidad) {
		try {
			return ResponseEntity.ok(this.service.guardar(entidad));

		} catch (Exception ex) {
			System.out.print(ex.toString());
			if (ex.toString().contains("ConstraintViolationException")) {
				return ResponseEntity.status(409).header("mensaje", "Ya existe una localizacion inmueble")
						.body((LocalizacionInmueble) null);
			}
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((LocalizacionInmueble) null);
		}
	}

	@PostMapping("/guardarLista")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LocalizacionInmueble>> guardar(@Valid @RequestBody List<LocalizacionInmueble> lista) {
		try {
			Long id = this.service.guardarLista(lista);
			return ResponseEntity.ok(this.service.obtenerLista(id));
		} catch (Exception ex) {
			System.out.print(ex.toString());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	@PostMapping("/eliminarLista")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> eliminarLista(@Valid @RequestBody List<LocalizacionInmueble> lista) {
		try {
			this.service.eliminarLista(lista);
			return ResponseEntity.ok("Se eliminó la lista con éxito");
		} catch (Exception ex) {
			System.out.print(ex.toString());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	@PostMapping("/eliminar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		try {
			this.service.eliminar(id);
			return ResponseEntity.ok("Se elimino correctamente");

		} catch (Exception ex) {
			System.out.print(ex.toString());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	@GetMapping("/obtenerLista")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LocalizacionInmueble>> obtenerLista(@RequestParam Long idPerfil) {
		try {
			List<LocalizacionInmueble> l = this.service.obtenerLista(idPerfil);
			return ResponseEntity.ok(l);

		} catch (Exception ex) {
			System.out.print(ex.toString());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}
	
	@GetMapping("/obtenerGraficoPorProvincia")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ReporteGraficosModel>> obtenerDatosPorProvincia(){
		try {
			List<ReporteGraficosModel> reporte = new ArrayList<ReporteGraficosModel>();
			reporte = repoProvincia.obtenerDatos();
			return ResponseEntity.ok(reporte);

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((List<ReporteGraficosModel>) null);
		}
	}
	
	@GetMapping("/ObtenerPerfilesConInmuebles")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Integer> obtenerPerfilesConInmuebles(){
		try {
			return ResponseEntity.ok(this.service.cantidadPerfilesConInmuebles());

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Integer) null);
		}
	}
	
	@GetMapping("/obtenerInmuebleById")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LocalizacionInmueble> obtenerInmuebleById(@RequestParam Long id){
		try {
			return ResponseEntity.ok(this.service.obtenerInmuebleById(id));

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((LocalizacionInmueble) null);
		}
	}
	
	@PostMapping("/realizarAccion")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LocalizacionInmueble> realizarAccion(@RequestBody LocalizacionInmueble inmueble) {
		try {
			LocalizacionInmueble inmuebleActualizado = new LocalizacionInmueble();
			if (inmueble.getId() != null) {
				inmuebleActualizado = this.service.guardar(inmueble);

				return ResponseEntity.ok(inmuebleActualizado);
			}
			return ResponseEntity.status(409).header("mensaje", "No se encontro el inmueble")
					.body((LocalizacionInmueble) null);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((LocalizacionInmueble) null);
		}
	}

	@PostMapping("/enviarCorreo")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LocalizacionInmueble> enviarCorreo(@RequestBody LocalizacionInmueble inmueble, @RequestParam String descripcion, @RequestParam String accion, @RequestParam String param) {
		try {
				if(inmueble.getCorreo()!= null || inmueble.getCorreo() != "") {
					if (accion.equalsIgnoreCase("rechazar")) {
						this.notificaciones.enviarCorreoParam(inmueble.getCorreo(), descripcion, accion, param);
					}else {
						this.notificaciones.enviarCorreoParam(inmueble.getCorreo(),descripcion, accion, param);
					}
					return ResponseEntity.ok(inmueble);
				}else {
					return ResponseEntity.status(400).header("mensaje", "No se encontro ningun correo para notificaciones").body((LocalizacionInmueble)null);
				}
			
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(((LocalizacionInmueble)null) );
		}
	}
}
