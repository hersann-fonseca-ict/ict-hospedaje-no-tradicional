package com.ict.perfiles.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ict.commons.entity.Empresa;
import com.ict.commons.entity.EstablecimientosPorAnalista;
import com.ict.commons.entity.LocalizacionInmueble;
import com.ict.commons.entity.Perfil;
import com.ict.commons.entity.PerfilView;
import com.ict.commons.entity.PerfilViewEM;
import com.ict.commons.entity.Perfiles;
import com.ict.commons.entity.RolesSistema;
import com.ict.commons.entity.UsuarioSistema;
import com.ict.commons.models.ReporteGraficosModel;
import com.ict.perfiles.models.CorreoModel;
import com.ict.perfiles.models.PerfilExcelModel;
import com.ict.perfiles.repositories.ILocalizacionInmuebleRepository;
import com.ict.perfiles.repositories.INotificacionesFeign;
import com.ict.perfiles.repositories.IPerfilesRepositoryTabla;
import com.ict.perfiles.repositories.IUsuarioSistemaRepository;
import com.ict.perfiles.services.IEmpresaService;
import com.ict.perfiles.services.IEstablecimientosPorAnalistaService;
import com.ict.perfiles.services.IPerfilService;

import io.swagger.annotations.ApiOperation;

@RestController
public class PerfilesController {

	@Autowired
	private IPerfilService service;
	@Autowired
	private IPerfilesRepositoryTabla repository;

	@Autowired
	private INotificacionesFeign notificaciones;

	@Autowired
	private IEmpresaService empresaService;

	@Autowired
	private IEstablecimientosPorAnalistaService establecimientosPorAnalistaService;

	@Autowired
	private IUsuarioSistemaRepository iUsuarioSistemaRepository;

	@Autowired
	private ILocalizacionInmuebleRepository iLocalizacionInmuebleRepository;

	@GetMapping("/obtenerPorNombreUsuario")
	public ResponseEntity<Perfil> obtener(@RequestParam String nombreUsuario) throws IOException {
		try {
			// Verifica si el nombre de usuario es null o vacio
			if (nombreUsuario != null && !nombreUsuario.isBlank()) {
				return ResponseEntity.ok(this.service.obtenerPorNombreUsuario(nombreUsuario));
			}
			return ResponseEntity.badRequest().header("mensaje", "El nombre de usuario es requerido")
					.body((Perfil) null);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Perfil) null);
		}
	}

	@PostMapping("/guardar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Perfil> guardar(@RequestBody Perfil perfil) {
		try {

			Perfil perfilNuevo = new Perfil();
			Perfil existeIdentificacion = this.service.obtenerPorIdentificación(perfil);
			if (existeIdentificacion != null && !existeIdentificacion.getId().equals(perfil.getId())) {
				return ResponseEntity.status(409).header("mensaje", "Existe un perfil con la identificación")
						.body((Perfil) null);
			}
			if (perfil.getId() == null) {
				perfilNuevo = this.service.guardar(perfil);
				this.notificaciones.enviarCorreo(perfil.getCorreo(), "Confirmación de registro", "registroPerfil");
				return ResponseEntity.ok(perfilNuevo);
			}
			if (perfil.getId() != null && (perfil.getSolicitud() == null || perfil.getSolicitud().isEmpty())) {
				perfilNuevo = this.service.guardar(perfil);
				this.notificaciones.enviarCorreo(perfil.getCorreo(), "Actualización del perfil", "modificacionPerfil");
				return ResponseEntity.ok(perfilNuevo);
			}
			throw new Exception("Error no controlado");
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Perfil) null);
		}
	}

	@PostMapping("/nuevoCliente")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Perfil> nuevoCliente(@Valid @RequestBody Perfil perfil) {
		try {

			// Rol de cliente
			RolesSistema rol = new RolesSistema();
			rol.setId(23L);

			perfil.getUsuarioSistema().getRoles().clear();
			perfil.getUsuarioSistema().getRoles().add(rol);

			return ResponseEntity.ok(this.service.guardar(perfil));
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Perfil) null);
		}
	}

	@ApiOperation(value = "obtenerTabla", notes = "Obtiene Perfiles")
	@PostMapping("/obtenerTabla")
	public DataTablesOutput<Perfiles> getTabla(@Valid @RequestBody DataTablesInput input) {
		try {
			return repository.findAll(input);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@ApiOperation(value = "obtenerTablaActivos", notes = "Obtiene Perfiles Activos")
	@PostMapping("/obtenerTablaActivos")
	public DataTablesOutput<Perfiles> getTablaActivos(@Valid @RequestBody DataTablesInput input) {
		try {
			List<Perfiles> listaActivos = repository
					.findAllByAprobarInmuebleTrueAndDesafiliarInmuebleFalse();
			DataTablesOutput<Perfiles> respuesta = new DataTablesOutput<Perfiles>();
			respuesta.setData(listaActivos);
			respuesta.setRecordsFiltered(listaActivos.size());
			respuesta.setRecordsTotal(listaActivos.size());
			return respuesta;
			// return repository.findAll(input);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@ApiOperation(value = "obtenerPorId", notes = "Obtiene por id Perfil")
	@GetMapping("/obtenerPorId")
	public ResponseEntity<Perfil> obtenerPorId(@RequestParam Long id) {
		try {
			Perfil per = service.obtenerPorId(id);
			return ResponseEntity.ok(per);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Perfil) null);
		}
	}

	@ApiOperation(value = "obtenerListadoExcel", notes = "Obtiene el listado de perfiles aprobados")
	@GetMapping("/obtenerListadoExcel")
	public ResponseEntity<List<PerfilExcelModel>> obtenerListadoExcel() {
		try {
			List<PerfilExcelModel> lista = service.obtenerListaPerfilesExcel();
			return ResponseEntity.ok(lista);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((List<PerfilExcelModel>) null);
		}
	}

	@PostMapping("/realizarAccion")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Perfil> realizarAccion(@RequestBody Perfil perfil, @RequestParam String descripcion,
			@RequestParam String accion) {
		try {
			Perfil perfilNuevo = new Perfil();
			if (perfil.getId() != null && (perfil.getSolicitud() == null || perfil.getSolicitud().isEmpty())) {
				perfilNuevo = this.service.guardar(perfil);
				if (perfil.getCorreo() != null || perfil.getCorreo() != "") {
					if (accion.equalsIgnoreCase("rechazar")) {
						this.notificaciones.enviarCorreoParam(perfil.getCorreo(), descripcion, accion, "");
					} else {
						this.notificaciones.enviarCorreo(perfil.getCorreo(), descripcion, accion);
					}

				}
				return ResponseEntity.ok(perfilNuevo);
			}
			return ResponseEntity.status(409).header("mensaje", "Existe un perfil con esa identificacion")
					.body((Perfil) null);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Perfil) null);
		}
	}

	@GetMapping("/obtenerGraficoTipoServicio")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ReporteGraficosModel>> obtenerDatosPorProvincia() {
		try {
			List<ReporteGraficosModel> reporte = new ArrayList<ReporteGraficosModel>();
			reporte = this.service.obtenerGraficoTipoServicio();
			return ResponseEntity.ok(reporte);

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((List<ReporteGraficosModel>) null);
		}
	}

	@GetMapping("/obtenerCantidadPerfiles")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> obtenerCantidadPerfiles() {
		try {
			return ResponseEntity.ok(this.service.obtenerCantidadPerfiles());

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Long) null);
		}
	}

	@ApiOperation(value = "obtenerPorId", notes = "Obtiene por id una Empresa")
	@GetMapping("/obtenerEmpresaPorId")
	public ResponseEntity<Empresa> obtenerEmpresaPorId(@RequestParam Long id) {
		try {
			Empresa empresa = this.empresaService.obtenerEmpresaPorId(id);
			return ResponseEntity.ok(empresa);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Empresa) null);
		}
	}

	@PostMapping("/realizarAccionEmpresa")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Empresa> actualizarEmpresa(@RequestBody Empresa empresa) {
		try {
			Empresa empresaNueva = new Empresa();
			if (empresa.getId() != null) {
				empresaNueva = this.empresaService.actualizarEmpresa(empresa);
				return ResponseEntity.ok(empresaNueva);
			}
			return ResponseEntity.status(404).header("mensaje", "No se encontro la empresa").body((Empresa) null);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Empresa) null);
		}
	}

	@PostMapping("/enviarCorreoEmpresa")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Empresa> enviarCorreoEmpresa(@RequestBody Empresa empresa, @RequestParam String descripcion,
			@RequestParam String accion, @RequestParam String param) {
		try {
			if (empresa.getCorreo() != null || empresa.getCorreo() != "") {
				if (accion.equalsIgnoreCase("rechazar")) {
					this.notificaciones.enviarCorreoParam(empresa.getCorreo(), descripcion, accion, param);
				} else {
					this.notificaciones.enviarCorreoParam(empresa.getCorreo(), descripcion, accion, param);
				}
				return ResponseEntity.ok(empresa);
			} else {
				return ResponseEntity.status(400).header("mensaje", "No se encontro ningun correo para notificaciones")
						.body((Empresa) null);
			}

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(((Empresa) null));
		}
	}

	@ApiOperation(value = "obtenerTablaEmpresas", notes = "Obtiene Perfiles")
	@PostMapping("/obtenerTablaEmpresas")
	public DataTablesOutput<PerfilView> getTablaEmpresasInscritas(@Valid @RequestBody DataTablesInput input) {
		return service.getTablaEstablecimientosAprobados(input);
	}
	
    @ApiOperation(value = "obtenerTablaEmpresasEm", notes = "Obtiene Perfiles")
    @PostMapping("/obtenerTablaEmpresasEm")
    public DataTablesOutput<PerfilViewEM> getTablaEmpresasEmInscritas(@Valid @RequestBody DataTablesInput input) {
        return service.getTablaEstablecimientosEmAprobados(input);
    }

	@ApiOperation(value = "obtenerEstablecimentosPorUsuario", notes = "Obtener los establecimientos según el usuario")
	@PostMapping("/obtenerEstablecimentosPorUsuario")
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPorUsuario(@Valid @RequestBody DataTablesInput input,
			@RequestParam Long idUsuario) {
		return service.getTablaEstablecimientosPorUsuario(input, idUsuario);
	}

	@ApiOperation(value = "obtenerEstablecimentosPendientesAprobar", notes = "Obtener los establecimientos pendientes de aprobar por el jefe")
	@PostMapping("/obtenerEstablecimentosPendientesAprobar")
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPendientesAprobar(
			@Valid @RequestBody DataTablesInput input) {
		return service.getTablaEstablecimientosPendientesAprobar(input);
	}

	@ApiOperation(value = "obtenerEstablecimentosPendientesDesafiliar", notes = "Obtener los establecimientos pendientes de desafiliar por el jefe")
	@PostMapping("/obtenerEstablecimentosPendientesDesafiliar")
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPendientesDesafiliar(
			@Valid @RequestBody DataTablesInput input) {
		return service.getTablaEstablecimientosPendientesDesafiliar(input);
	}

	@ApiOperation(value = "asignarEstablecimentoAAnalista", notes = "Asigna un establecimiento a un analista")
	@GetMapping("/asignarEstablecmientoAnalista")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EstablecimientosPorAnalista> asignarEstablecimentoAAnalista(@RequestParam Long idUsuario,
			@RequestParam Long idLocalizacion, @RequestParam Long idEmpresa, @RequestParam Long idJefe) {
		try {
			EstablecimientosPorAnalista establecimientoNuevo = this.establecimientosPorAnalistaService
					.asignarEstablecimientoPorAnalista(
							crearEstablecimiento(idUsuario, idLocalizacion, idEmpresa, idJefe));
			return ResponseEntity.ok(establecimientoNuevo);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((EstablecimientosPorAnalista) null);
		}
	}

	@ApiOperation(value = "quitarAsignacionEstablecimientosPorUsuario", notes = "Quita el establecimiento asignado a un analista")
	@DeleteMapping("/quitarAsignacionEstablecimientoAnalista")
	public ResponseEntity<EstablecimientosPorAnalista> quitarAsignacionEstablecimientosPorUsuario(
			@RequestParam Long idUsuario, @RequestParam(required = false) Long idLocalizacion,
			@RequestParam(required = false) Long idEmpresa, @RequestParam Long idJefe) {
		try {
			EstablecimientosPorAnalista establecimiento = this.establecimientosPorAnalistaService
					.obtieneEstablecimiento(crearEstablecimiento(idUsuario, idLocalizacion, idEmpresa, idJefe));
			boolean res = this.establecimientosPorAnalistaService
					.quitarAsignacionEstablecimientoPorAnalista(establecimiento);
			return ResponseEntity.ok(res ? establecimiento : null);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	private EstablecimientosPorAnalista crearEstablecimiento(Long idUsuario, Long idLocalizacion, Long idEmpresa,
			Long idJefe) {
		EstablecimientosPorAnalista establecimiento = new EstablecimientosPorAnalista();

		if (idUsuario != null && idUsuario != 0) {
			UsuarioSistema usuario = iUsuarioSistemaRepository.findById(idUsuario).orElse(null);
			establecimiento.setUsuarioSistema(usuario);
		}

		if (idLocalizacion != null && idLocalizacion != 0) {
			LocalizacionInmueble localizacionInmueble = iLocalizacionInmuebleRepository.findById(idLocalizacion)
					.orElse(null);
			establecimiento.setLocalizacion(localizacionInmueble);
		}

		if (idEmpresa != null && idEmpresa != 0) {
			Empresa empresa = empresaService.obtenerEmpresaPorId(idEmpresa);
			establecimiento.setEmpresa(empresa);
		}

		if (idJefe != null && idJefe != 0) {
			UsuarioSistema jefe = iUsuarioSistemaRepository.findById(idJefe).orElse(null);
			establecimiento.setJefe(jefe);
		}

		return establecimiento;
	}

	@ApiOperation(value = "enviarCorreoUsuario", notes = "Envía un correo a un usuario")
	@PostMapping("/enviarCorreoUsuario")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CorreoModel> enviarCorreoUsuario(@RequestParam Long idUsuario,
			@RequestBody CorreoModel correo) {
		try {
			String destino;
			if (idUsuario != null) {
				UsuarioSistema usuario = iUsuarioSistemaRepository.findById(idUsuario).orElse(null);
				if (usuario == null) {
					throw new Exception("El usuario no existe!");
				}
				destino = usuario.getCorreo();
			} else {
				if (correo.getDestino() == null) {
					throw new Exception("El destino del correo es inválido!");
				}
				destino = correo.getDestino();
			}
			this.notificaciones.enviarCorreoParam(destino, correo.getAsunto(), "asignar", correo.getCuerpo());
			correo.setEstado("Se envió el correo exitosamente");
			return ResponseEntity.ok(correo);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			correo.setEstado("Ocurrió un problema al enviar el correo");
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(correo);
		}
	}

	@ApiOperation(value = "enviarCorreoUsuarioPerfil", notes = "Envía un correo a un usuario")
	@PostMapping("/enviarCorreoUsuarioPerfil")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CorreoModel> enviarCorreoUsuarioPerfil(@RequestParam String emailDestinatario,
			@RequestBody CorreoModel correo) {
		try {
			this.notificaciones.enviarCorreoParam(emailDestinatario, correo.getAsunto(), "asignar", correo.getCuerpo());
			correo.setEstado("Se envió el correo exitosamente");
			return ResponseEntity.ok(correo);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			correo.setEstado("Ocurrió un problema al enviar el correo");
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(correo);
		}
	}
	
	@ApiOperation(value = "obtenerListadoExcelEmpresa", notes = "Obtiene el listado de perfiles aprobados")
	@GetMapping("/obtenerListadoExcelEmpresa")
	public ResponseEntity<List<PerfilExcelModel>> obtenerListadoExcelEmpresa() {
		try {
			List<PerfilExcelModel> lista = service.obtenerListaPerfilesExcelEmpresa();
			return ResponseEntity.ok(lista);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((List<PerfilExcelModel>) null);
		}
	}
}
