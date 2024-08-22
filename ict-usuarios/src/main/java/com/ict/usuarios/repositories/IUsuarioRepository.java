package com.ict.usuarios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.UsuarioSistema;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioSistema, Long> {

	@Query(value = "SELECT a FROM UsuarioSistema a WHERE a.nombreUsuario like ?1", nativeQuery = false)
	UsuarioSistema findUsuarioSistemaByNombreUsuario(String nombreUsuario);

	UsuarioSistema findFirstByOrderByIdDesc();

	UsuarioSistema findUsuarioSistemaByCorreo(String correo);

	@Query(value = "SELECT a FROM UsuarioSistema a "
			+ "INNER JOIN UsuariosSistemaRoles b "
			+ "ON a.id=b.usuarioSistemaRolesPK.id_usuario "
			+ "WHERE b.usuarioSistemaRolesPK.id_rol = ?1", nativeQuery = false)
	List<UsuarioSistema> findUsuariosSistemaPorRolId(Long rolId);

}
