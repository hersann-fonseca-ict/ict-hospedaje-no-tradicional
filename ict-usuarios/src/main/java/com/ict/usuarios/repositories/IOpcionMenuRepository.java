package com.ict.usuarios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ict.commons.entity.RolSistemaOpcion;
 
@Repository
public interface IOpcionMenuRepository extends JpaRepository<RolSistemaOpcion, Long> {
//SELECT  b.* FROM USUARIOS_SISTEMA_ROLES a INNER JOIN ROL_SISTEMA_OPCION b  ON a.ID_ROL = b.ID_ROL WHERE a.ID_USUARIO  =  2
	@Query(value = "SELECT b FROM UsuariosSistemaRoles a INNER JOIN RolSistemaOpcion b  ON b.roles_sistema.id = a.usuarioSistemaRolesPK.id_rol  WHERE a.usuarioSistemaRolesPK.id_usuario  = ?1")
	List<RolSistemaOpcion> listaOpcionesMenu(Long id);
	
}
