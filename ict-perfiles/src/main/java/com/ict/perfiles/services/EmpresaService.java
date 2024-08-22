package com.ict.perfiles.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.Empresa;
import com.ict.perfiles.repositories.IEmpresaRepository;

@Service
public class EmpresaService implements IEmpresaService {

	@Autowired
	private IEmpresaRepository empresaRepository;
	
	@Override
	public Empresa obtenerEmpresaPorId(Long id) {
		Empresa empresa = this.empresaRepository.findById(id).get();	
		if (empresa != null && empresa.getId() != null) {
			return empresa;
		}else {
			return null;
		}
	}

	@Override
	public Empresa actualizarEmpresa(Empresa empresa) {
		Empresa respuesta = this.empresaRepository.save(empresa);
		return respuesta;
	}

}
