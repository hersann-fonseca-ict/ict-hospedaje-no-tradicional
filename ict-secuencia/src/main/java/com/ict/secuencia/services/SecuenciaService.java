package com.ict.secuencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.Secuencia;
import com.ict.secuencia.repositories.ISecuencia;

@Service
public class SecuenciaService implements ISecuenciaService {

	@Autowired
	private ISecuencia repository;

	@Override
	public Secuencia guardar(Secuencia entidad) {
		Secuencia sec = this.obtener(entidad.getId());
		entidad.setSecuencia(sec.getSecuencia() != null ? sec.getSecuencia() + 1L : 1L);
		Secuencia nueva = this.repository.saveAndFlush(entidad);
		return nueva;
	}

	@Override
	public Secuencia obtener(String id) {
		return this.repository.findById(id).get();
	}
}
