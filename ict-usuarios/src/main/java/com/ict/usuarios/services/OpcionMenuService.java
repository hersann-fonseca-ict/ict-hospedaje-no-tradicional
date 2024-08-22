package com.ict.usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ict.commons.entity.RolSistemaOpcion;
import com.ict.usuarios.repositories.IOpcionMenuRepository;


@Service
public class OpcionMenuService implements IOpcionMenuService {

	@Autowired
	private IOpcionMenuRepository repository;

	@Override
	public List<RolSistemaOpcion> obtenerOpcionesPorIdUsuario(Long id) {	
		List<RolSistemaOpcion> opciones = this.repository.listaOpcionesMenu(id);
	return opciones;
		
	}

}
