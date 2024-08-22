package com.ict.secuencia.services;

import com.ict.commons.entity.Secuencia;

public interface ISecuenciaService {
	public Secuencia guardar(Secuencia entidad);

	public Secuencia obtener(String id);
	
}
