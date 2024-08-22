package com.ict.perfiles.services;

import com.ict.commons.entity.Empresa;

public interface IEmpresaService {

	public Empresa obtenerEmpresaPorId(Long id);
	
	public Empresa actualizarEmpresa(Empresa empresa);
}
