package com.ict.perfiles.services;

import java.util.List;

import com.ict.commons.entity.EstablecimientosPorAnalista;
import com.ict.commons.entity.UsuarioSistema;

public interface IEstablecimientosPorAnalistaService {

	public List<EstablecimientosPorAnalista> obtenerEstablecimientosPorAnalista(UsuarioSistema usuario);

	public EstablecimientosPorAnalista asignarEstablecimientoPorAnalista(EstablecimientosPorAnalista establecimiento);

	public boolean quitarAsignacionEstablecimientoPorAnalista(EstablecimientosPorAnalista establecimiento);

	public EstablecimientosPorAnalista obtieneEstablecimiento(EstablecimientosPorAnalista establecimiento);
	
}
