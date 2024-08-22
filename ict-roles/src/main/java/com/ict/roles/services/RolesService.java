package com.ict.roles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.RolesSistema;
import com.ict.roles.repositories.IRolesRepository;

@Service
public class RolesService implements IRolesService {

	@Autowired
	private IRolesRepository rolRepository;

	@Override
	public RolesSistema guardar(RolesSistema rol) {
		if (rol.getId() == null) {
			rol.setId(this.obtenerProxId());
		}
		return this.rolRepository.save(rol);
	}

	@Override
	public RolesSistema obtenerPorId(Long id) {
		return this.rolRepository.getOne(id);
	}

	@Override
	public List<RolesSistema> obtener() {
		return this.rolRepository.findAll();
	}

	private Long obtenerProxId() {
		RolesSistema entidad = this.rolRepository.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}
}
