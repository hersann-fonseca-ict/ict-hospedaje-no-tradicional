package com.ict.tiposervicios.services;

import java.util.List;

import com.ict.commons.entity.TipoServicio;

public interface ITipoServicioService {
	
	public TipoServicio guardar(TipoServicio tipoServicio);
	public TipoServicio obtenerPorId(Long id);
	public List<TipoServicio> obtener();
	
}
