package com.ict.usuarios.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.UsuarioSistema;
import com.ict.usuarios.repositories.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioRepository repository;

	@Override
	public UsuarioSistema guardar(UsuarioSistema usuario) {
		UsuarioSistema existente = this.obtenerPorNombreUsuario(usuario.getNombreUsuario());

		if (existente == null || (existente != null && !existente.getClave().contentEquals(usuario.getClave()))) {
			usuario.setClave(passwordEncoder.encode(usuario.getClave()));
		}
		if (usuario.getId() == null) {
			usuario.setId(this.obtenerProxId());
		}
		usuario.setFechaCreacion(new Date());
		UsuarioSistema nuevoUsuario = this.repository.save(usuario);
		return nuevoUsuario;
	}

	@Override
	public UsuarioSistema actualizar(UsuarioSistema usuario) {
		UsuarioSistema existente = this.obtenerPorNombreUsuario(usuario.getNombreUsuario());

		if (existente == null || (existente != null && !existente.getClave().contentEquals(usuario.getClave()))) {
			usuario.setClave(passwordEncoder.encode(usuario.getClave()));
		}

		usuario.setFechaCreacion(new Date());
		UsuarioSistema nuevoUsuario = this.repository.save(usuario);
		return nuevoUsuario;
	}

	@Override
	public List<UsuarioSistema> obtener() {
		return this.repository.findAll();
	}

	@Override
	public UsuarioSistema obtenerPorNombreUsuario(String nombreUsuario) {
		return this.repository.findUsuarioSistemaByNombreUsuario(nombreUsuario);
	}

	@Override
	public String generarCodigoVerificacion() {
		return this.obtenerAlphaNumerico(8);
	}

	@Override
	public void asignarCodigoVerificacion(String nombreUsuario, String codigo) {
		UsuarioSistema usuario = this.obtenerPorNombreUsuario(nombreUsuario);
		if (usuario != null) {
			usuario.setUltimoCodigoVerificacion(codigo);
			usuario.setActivo(false);
			this.guardar(usuario);
		}
	}

	@Override
	public boolean verificarCodigo(String nombreUsuario, String codigoVerificacion) {
		UsuarioSistema usuario = this.obtenerPorNombreUsuario(nombreUsuario);
		if (usuario != null && usuario.getUltimoCodigoVerificacion() != null
				&& usuario.getUltimoCodigoVerificacion().contentEquals(codigoVerificacion)) {
			return true;
		}
		return false;
	}

	@Override
	public UsuarioSistema asignarNuevaClave(String nombreUsuario, String nuevaClave) {
		UsuarioSistema usuario = this.obtenerPorNombreUsuario(nombreUsuario);
		usuario.setActivo(true);
		usuario.setClave(passwordEncoder.encode(nuevaClave));

		usuario.setUltimoCodigoVerificacion(null);
		UsuarioSistema usuarioActualizado = this.guardar(usuario);
		return usuarioActualizado;
	}

	@Override
	public UsuarioSistema activarInactivar(String nombreUsuario, String activo) {
		UsuarioSistema usuario = this.obtenerPorNombreUsuario(nombreUsuario);
		usuario.setActivo(Boolean.valueOf(activo));
		usuario.setUltimoCodigoVerificacion(null);
		UsuarioSistema usuarioActualizado = this.guardar(usuario);
		return usuarioActualizado;
	}

	private String obtenerAlphaNumerico(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	private Long obtenerProxId() {
		UsuarioSistema entidad = this.repository.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}

	@Override
	public Long cantidadUsuariosRegistrados() {
		return this.repository.count();
	}

	@Override
	public Long obtieneIdJefePorCorreo(String correoJefe) {
		return repository.findUsuarioSistemaByCorreo(correoJefe).getId();
	}

	@Override
	public List<UsuarioSistema> obtieneUsuariosPorRolId(Long rolId) {
		return repository.findUsuariosSistemaPorRolId(rolId);
	}

}
