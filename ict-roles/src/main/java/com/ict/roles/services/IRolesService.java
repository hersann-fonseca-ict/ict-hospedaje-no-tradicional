package com.ict.roles.services;

import java.util.List;

import com.ict.commons.entity.RolesSistema;

public interface IRolesService {

	public RolesSistema guardar(RolesSistema rol);
	public RolesSistema obtenerPorId(Long id);
	public List<RolesSistema> obtener();
	
}
