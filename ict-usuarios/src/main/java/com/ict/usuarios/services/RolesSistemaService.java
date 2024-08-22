package com.ict.usuarios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.RolesSistema;
import com.ict.usuarios.repositories.IRolesSistemaRepository;
import java.util.List;

@Service
public class RolesSistemaService implements IRolesSistemaService {
	@Autowired
	private IRolesSistemaRepository repository;

	@Override
	public List<RolesSistema> lista() {
		return repository.findAll();
	}

}
