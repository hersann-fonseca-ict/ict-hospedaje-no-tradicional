package com.ict.localizacioninmueble.services;

import java.util.List;

import com.ict.commons.entity.LocalizacionInmueble;

public interface ILocalizacionInmuebleService {
	public LocalizacionInmueble guardar(LocalizacionInmueble entidad);
	
	public Long guardarLista(List<LocalizacionInmueble> lista);
	
	public void eliminarLista(List<LocalizacionInmueble> lista);
	
	public void eliminar(Long id);
	
	public List<LocalizacionInmueble> obtenerLista(Long idPerfil);
	
	public Integer cantidadPerfilesConInmuebles();
	
	public LocalizacionInmueble obtenerInmuebleById(Long id);
	
}
