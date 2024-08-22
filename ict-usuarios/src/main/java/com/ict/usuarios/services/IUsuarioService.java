package com.ict.usuarios.services;

import java.util.List;

import com.ict.commons.entity.UsuarioSistema;

public interface IUsuarioService {
	public UsuarioSistema guardar(UsuarioSistema usuario);

	public UsuarioSistema actualizar(UsuarioSistema usuario);

	public List<UsuarioSistema> obtener();

	public UsuarioSistema obtenerPorNombreUsuario(String nombreUsuario);

	public String generarCodigoVerificacion();

	public void asignarCodigoVerificacion(String nombreUsuario, String codigo);

	public boolean verificarCodigo(String nombreUsuario, String codigoVerificacion);

	public UsuarioSistema asignarNuevaClave(String nombreUsuario, String nuevaClave);

	public UsuarioSistema activarInactivar(String nombreUsuario, String activo);

	public Long cantidadUsuariosRegistrados();

	public Long obtieneIdJefePorCorreo(String correoJefe);
	
	public List<UsuarioSistema> obtieneUsuariosPorRolId(Long rolId);

}
