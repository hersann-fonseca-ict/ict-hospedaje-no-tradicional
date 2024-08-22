package com.ict.beneficios.services;

import java.util.List;

import com.ict.beneficios.models.BeneficiosPaginados;
import com.ict.commons.entity.Beneficio;

public interface IBeneficiosService {
	
	public Beneficio guardar(Beneficio beneficio);
	
	public Beneficio obtenerPorId(Long id);
	
	public List<Beneficio> obtener();
	
	
	public BeneficiosPaginados obtenerPaginado(int pagina, int tamano, String columna);

	public BeneficiosPaginados obtenerPrueba();
	
	public Beneficio obtenerPorNombre(Beneficio beneficio);
	
	public Long obtenerProxId();
}
