package com.ict.usuarios.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ict.commons.entity.RolesSistema;
import com.ict.commons.entity.UsuarioSistema;
import com.ict.commons.entity.UsuariosSistemaRoles;
import com.ict.commons.entity.UsuariosSistemaRolesPK;
import com.ict.usuarios.feigns.INotificacionesFeign;
import com.ict.usuarios.repositories.IUsuarioRepositoryTable;
import com.ict.usuarios.services.IUsuarioService;
import com.ict.usuarios.services.IRolesSistemaService;
import static java.util.stream.Collectors.toList;
import io.swagger.annotations.ApiOperation;

@RestController
public class UsuariosController {

	@Autowired
	private INotificacionesFeign notificaciones;

	@Autowired
	private IUsuarioService service;
	@Autowired
	private IUsuarioRepositoryTable repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IRolesSistemaService rolesSistemaService;

	@GetMapping("/obtenerPorNombreUsuario")
	public ResponseEntity<UsuarioSistema> obtenerPorNombreUsuario(@RequestParam String nombreUsuario)
			throws IOException {
		try {
			// Verifica si el nombre de usuario es null o vacio
			if (nombreUsuario != null && !nombreUsuario.isBlank()) {
				return ResponseEntity.ok(this.service.obtenerPorNombreUsuario(nombreUsuario));
			}
			return ResponseEntity.badRequest().header("mensaje", "El nombre de usuario es requerido")
					.body((UsuarioSistema) null);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((UsuarioSistema) null);
		}
	}

	@PostMapping("/guardar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioSistema> guardar(@Valid @RequestBody UsuarioSistema usuario) {
		try {
			return ResponseEntity.ok(this.service.guardar(usuario));

		} catch (Exception ex) {
			System.out.print(ex.toString());
			if (ex.toString().contains("ConstraintViolationException")) {
				return ResponseEntity.status(409).header("mensaje", "Ya existe un usuario con ese nombre")
						.body((UsuarioSistema) null);
			}
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((UsuarioSistema) null);
		}
	}

	@PostMapping("/actualizar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioSistema> actualizar(@Valid @RequestBody UsuarioSistema usuario) {
		try {
			UsuarioSistema temporal = this.service.obtenerPorNombreUsuario(usuario.getNombreUsuario());
			if (temporal == null && usuario.getId() == null) {
				return ResponseEntity.ok(this.service.guardar(usuario));
			} else if (temporal != null && usuario.getId() == temporal.getId()) {
				return ResponseEntity.ok(this.service.guardar(usuario));
			}
			return ResponseEntity.status(409).header("mensaje", "Ya existe un usuario con ese nombre")
					.body((UsuarioSistema) null);

		} catch (Exception ex) {

			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((UsuarioSistema) null);
		}
	}

	@PostMapping("/nuevoCliente")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioSistema> nuevoCliente(@Valid @RequestBody UsuarioSistema usuario) {
		try {
			if (this.service.obtenerPorNombreUsuario(usuario.getNombreUsuario()) == null) {
				usuario.setRoles(new ArrayList<RolesSistema>());
				String code = this.service.generarCodigoVerificacion();
				usuario.setUltimoCodigoVerificacion(code);
				RolesSistema rol = new RolesSistema();
				rol.setId(23L);
				usuario.getRoles().add(rol);
				UsuarioSistema nuevoCliente = this.service.guardar(usuario);
				this.notificaciones.enviarCorreoParam(nuevoCliente.getCorreo(),
						"Su usuario en el Registro de Empresas y Actividades Turísticas se ha creado",
						"registroUsuario", code);
				return ResponseEntity.ok(nuevoCliente);
			}
			return ResponseEntity.status(409).header("mensaje", "Ya existe un usuario con ese nombre")
					.body((UsuarioSistema) null);

		} catch (Exception ex) {

			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((UsuarioSistema) null);
		}
	}

	@GetMapping("/obtener")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<UsuarioSistema>> obtener() {
		try {
			return ResponseEntity.ok(this.service.obtener());
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((List<UsuarioSistema>) null);
		}
	}

	@GetMapping(path = "/solicitarCodigoVerificacion")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> solicitarCodigoVerificacion(@RequestParam String username) {
		try {
			String code = this.service.generarCodigoVerificacion();
			this.service.asignarCodigoVerificacion(username, code);
			UsuarioSistema usuario = this.service.obtenerPorNombreUsuario(username);
			this.notificaciones.enviarCorreoParam(usuario.getCorreo(), "ICT - Solicitud de código de verificación",
					"solicitudCodigo", code);
			return ResponseEntity.ok(code);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	private String truncarTexto(String texto, int length) {
		return texto.length() <= length ? texto : texto.substring(0, length);
	}

	@GetMapping(path = "/usuarioNuevoToken")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioSistema> usuarioNuevoToken(@RequestParam String username, @RequestParam String id,
			@RequestParam String name) {
		try {
			String code = this.service.generarCodigoVerificacion();
			String usernameAux = username != null ? username.toLowerCase() : username;
			UsuarioSistema temporal = new UsuarioSistema();
			temporal = this.service.obtenerPorNombreUsuario(usernameAux);
			if (temporal == null) {
				temporal = new UsuarioSistema();
				temporal.setActivo(true);
				temporal.setCorreo(usernameAux);
				temporal.setNombreUsuario(usernameAux);
				temporal.setNombre(truncarTexto(name, 200));
				temporal.setClave(code);
				temporal.setUltimoCodigoVerificacion(code);
				temporal.setCodigoAcceso(passwordEncoder.encode(id));
				temporal.setRoles(
					rolesSistemaService.lista()
					.stream()
					.filter(r -> r.getId().equals(23L))
					.collect(toList())
					);
				temporal = this.service.guardar(temporal);
				try {
					this.notificaciones.enviarCorreoParam(temporal.getCorreo(), "ICT - Nuevo Usuario",
							"nuevoUsuarioToken", code);
				} catch (Exception ex) {
					return ResponseEntity.status(400)
							.header("mensaje", "Se creó el usuario pero ocurrió un problema al enviar el correo")
							.body(null);
				}
			} else if (temporal != null && temporal.getCodigoAcceso() == null) {
				temporal.setCodigoAcceso(passwordEncoder.encode(id));
				temporal.setNombre(truncarTexto(name, 200));
				temporal = this.service.actualizar(temporal);
			} else {
				temporal.setNombre(truncarTexto(name, 200));
				temporal = this.service.actualizar(temporal);
			}
			return ResponseEntity.ok(temporal);
		} catch (Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body(null);
		}
	}

	@GetMapping(path = "/cambiarClave")
	public ResponseEntity<UsuarioSistema> cambiarClave(@RequestParam String username,
			@RequestParam String verificationCode, @RequestParam String newPassword) throws IOException {
		boolean esValido = this.service.verificarCodigo(username, verificationCode);
		UsuarioSistema usuarioConNuevaClave = null;
		if (esValido) {
			usuarioConNuevaClave = this.service.asignarNuevaClave(username, newPassword);
			this.notificaciones.enviarCorreo(usuarioConNuevaClave.getCorreo(),
					"ICT - Notificación de cambio contraseña", "cambioContra");
		} else {
			return ResponseEntity.badRequest()
					.header("mensaje",
							"La contraseña del usuario " + username
									+ " fue cambiada correctamente, utilicela de ahora en adelante para iniciar sesión")
					.body((UsuarioSistema) null);
		}
		return ResponseEntity.ok(usuarioConNuevaClave);
	}

	@PostMapping(path = "/activarInactivar")
	public ResponseEntity<UsuarioSistema> activarInactivar(@RequestParam String nombreUsuario,
			@RequestParam String activo) throws IOException {
		UsuarioSistema usuarioAvtivarInactivar = null;
		usuarioAvtivarInactivar = this.service.activarInactivar(nombreUsuario, activo);
		return ResponseEntity.ok(usuarioAvtivarInactivar);
	}

	@GetMapping("/init")
	public ResponseEntity<UsuarioSistema> init() {

		if (this.service.obtener().isEmpty()) {
			UsuarioSistema usuario = new UsuarioSistema();
			usuario.setNombreUsuario("admin");
			usuario.setClave("Clave123456789$");
			usuario.setActivo(true);
			usuario.setCorreo("bryan.campos@gbsys.com");
			usuario.setRoles(new ArrayList<RolesSistema>());
			usuario.setFechaCreacion(new Date());

			RolesSistema rol = new RolesSistema();
			rol.setId(1L);

			usuario.getRoles().add(rol);
			return ResponseEntity.ok(this.service.guardar(usuario));
		}

		return null;

	}

	@ApiOperation(value = "ObtenerTable", notes = "Obtiene usuarios")
	@PostMapping("/ObtenerTable")
	public DataTablesOutput<UsuarioSistema> getTabla(@Valid @RequestBody DataTablesInput input) {
		return repository.findAll(input);
	}

	@ApiOperation(value = "getUsers", notes = "Obtiene usuarios")
	@GetMapping("/ObtenerUsuarios")
	public ResponseEntity<List<UsuarioSistema>> getUsers() {
		return ResponseEntity.ok(repository.findAll());
	}

	@ApiOperation(value = "ObtenerCantidadUsuarios", notes = "Obtiene cantidad de usuarios registrados")
	@GetMapping("/ObtenerCantidadUsuarios")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> obtenerCantidadUsuarios() {
		try {
			return ResponseEntity.ok(this.service.cantidadUsuariosRegistrados());

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error").body((Long) null);
		}
	}

	@ApiOperation(value = "obtienerUsuariosPorRolId", notes = "Obtiene todos los usuario dependienso el id del rol")
	@GetMapping("/obtienerUsuariosPorRolId")
	public ResponseEntity<List<UsuarioSistema>> obtienerUsuariosPorRolId(@RequestParam Long rolId) {
		return ResponseEntity.ok(service.obtieneUsuariosPorRolId(rolId));
	}

	@ApiOperation(value = "obtenerJefeIdPorCorreo", notes = "Obtiene el id del Jefe por el correo")
	@GetMapping("/obtenerJefeIdPorCorreo")
	public ResponseEntity<Long> obtenerJefeIdPorCorreo(@RequestParam String correoJefe) {
		return ResponseEntity.ok(service.obtieneIdJefePorCorreo(correoJefe));
	}

}
