package com.ict.roles.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.commons.entity.RolesSistema;
import com.ict.roles.services.IRolesService;

import io.swagger.annotations.ApiOperation;

@RestController
public class RolesController {
	
	@Autowired
	private IRolesService rolService;
	
	@PostMapping("/guardar")
	public ResponseEntity<RolesSistema> guardar(@RequestBody RolesSistema rolSistema){
		try {
			return ResponseEntity
					.ok(this.rolService.guardar(rolSistema));
		}catch(Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((RolesSistema)null);
		}
	}
	
	@GetMapping("/obtenerPorId")
	public ResponseEntity<RolesSistema> obtenerPorId(Long id){
		try {
			RolesSistema rolesSistema = this.rolService.obtenerPorId(id);
			if(rolesSistema != null) {
				return new ResponseEntity<RolesSistema>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity
					.ok(rolesSistema);
		}catch(Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((RolesSistema)null);
		}
	}
	
	@GetMapping("/obtener")
	public ResponseEntity<List<RolesSistema>> obtener(){
		try {
			return ResponseEntity
					.ok(this.rolService.obtener());
		}catch(Exception ex) {
			return ResponseEntity.status(500).header("mensaje", "Ha ocurrido un error")
					.body((List<RolesSistema>)null);
		}
	}
	
	@ApiOperation(value = "init", notes = "Inicializa los datos")
	@GetMapping("/init")
	public ResponseEntity<List<RolesSistema>> init(){
		if(this.rolService.obtener().isEmpty()) {
			this.rolService.guardar(new RolesSistema(1L, "ADMIN", "Administrador", true, new Date()));
			this.rolService.guardar(new RolesSistema(2L, "CLIENTE", "Cliente", true, new Date()));
			this.rolService.guardar(new RolesSistema(3L, "CREAR-USUARIO", "Crear usuarios", true, new Date()));
			this.rolService.guardar(new RolesSistema(4L, "MODIFICAR-USUARIO", "Modificar usuarios", true, new Date()));
			this.rolService.guardar(new RolesSistema(5L, "LISTAR-USUARIO", "Listar usuarios", true, new Date()));
			this.rolService.guardar(new RolesSistema(6L, "CREAR-ROL", "Crear rol", true, new Date()));
			this.rolService.guardar(new RolesSistema(7L, "MODIFICAR-ROL", "Modificar rol", true, new Date()));
			this.rolService.guardar(new RolesSistema(8L, "LISTAR-ROL", "Listar rol", true, new Date()));
			this.rolService.guardar(new RolesSistema(9L, "CREAR-TIPO-PERFIL", "Crear tipo perfil", true, new Date()));
			this.rolService.guardar(new RolesSistema(10L, "MODIFICAR-TIPO-PERFIL", "Modificar tipo perfil", true, new Date()));
			this.rolService.guardar(new RolesSistema(11L, "LISTAR-TIPO-PERFIL", "Listar tipo perfil", true, new Date()));
			this.rolService.guardar(new RolesSistema(12L, "CREAR-BENEFICIO", "Crear beneficio", true, new Date()));
			this.rolService.guardar(new RolesSistema(13L, "MODIFICAR-BENEFICIO", "Modificar beneficio", true, new Date()));
			this.rolService.guardar(new RolesSistema(14L, "LISTAR-BENEFICIO", "Listar beneficio", true, new Date()));
			this.rolService.guardar(new RolesSistema(15L, "CREAR-PERFIL", "Crear perfil", true, new Date()));
			this.rolService.guardar(new RolesSistema(16L, "MODIFICAR-PERFIL", "Modificar perfil", true, new Date()));
			this.rolService.guardar(new RolesSistema(17L, "LISTAR-PERFIL", "Listar perfil", true, new Date()));
			this.rolService.guardar(new RolesSistema(18L, "CREAR-SOLICITUD", "Crear solicitud", true, new Date()));
			this.rolService.guardar(new RolesSistema(19L, "MODIFICAR-SOLICITUD", "Modificar solicitud", true, new Date()));
			this.rolService.guardar(new RolesSistema(20L, "LISTAR-SOLICITUD", "Listar solicitud", true, new Date()));
		}
		return ResponseEntity.ok(this.rolService.obtener());
	}
	
}
