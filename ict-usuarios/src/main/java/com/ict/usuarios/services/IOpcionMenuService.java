package com.ict.usuarios.services;

import java.util.List;
 
import com.ict.commons.entity.RolSistemaOpcion;


public interface IOpcionMenuService {

	public List<RolSistemaOpcion> obtenerOpcionesPorIdUsuario(Long id);
	
	
}
