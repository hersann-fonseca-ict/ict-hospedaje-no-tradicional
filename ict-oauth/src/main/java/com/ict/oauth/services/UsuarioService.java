package com.ict.oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.ict.commons.entity.RolesSistema;
import com.ict.commons.entity.UsuarioSistema;
import com.ict.oauth.fegins.IUsuarioFeign;

import feign.FeignException;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private IUsuarioFeign usuario;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String nombreUsuario = null;
		String clave = null;
		Boolean activo = null;
		String user = null;
		List<GrantedAuthority> authorities;
		Boolean sistemaCA = false;
		try {
			if (username.contains("&")) {
				String[] vector = username.split("&");
				if (vector.length > 0) {
					user = vector[0];
					String ban = vector[1];
					sistemaCA = ban.equals("OCA");
				}
			}
			if (!sistemaCA) {
				user = username;
			}
			UsuarioSistema usuario = this.usuario.obtenerPorNombreUsuario(user);

			activo = usuario.isActivo();
			if (sistemaCA && usuario.getCodigoAcceso() != null && !usuario.getCodigoAcceso().isEmpty()) {
				clave = usuario.getCodigoAcceso();
				nombreUsuario = usuario.getNombreUsuario() + "&OCA";
			} else {
				nombreUsuario = usuario.getNombreUsuario();
				clave = usuario.getClave();
			}
			List<RolesSistema> roles = usuario.getRoles().stream().filter(o -> o.isActivo())
					.collect(Collectors.toList());

			authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getCodigo()))
					.collect(Collectors.toList());

			return new User(nombreUsuario, clave, activo, true, true, true, authorities);

		} catch (FeignException e) {
			String error = "Error en el login, no existe el usuario '" + username + "' en el sistema";
			throw new UsernameNotFoundException(error);
		}
	}

}
