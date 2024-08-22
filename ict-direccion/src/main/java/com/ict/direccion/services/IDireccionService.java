package com.ict.direccion.services;

import java.util.List;

import com.ict.commons.entity.Canton;
import com.ict.commons.entity.Distrito;
import com.ict.commons.entity.Provincia;

public interface IDireccionService {
	
	
	public Provincia obtenerProvinciaPorId(Long id);
	public List<Provincia> obtenerProvincias();
	
	public List<Canton> obtenerCantonPorId(Long id);
	public List<Canton> obtenerCantones();
	public String obtenerCantonNombre(Long id);
	
	public List<Distrito> obtenerDistritosPorId(Long id);
	public List<Distrito> obtenerDistritos();
	public String obtenerDistritoNombre(Long id);
	
}
