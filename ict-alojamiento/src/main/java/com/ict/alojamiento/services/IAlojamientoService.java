package com.ict.alojamiento.services;

import java.util.List;

import com.ict.alojamiento.models.AlojamientoModel;
import com.ict.commons.entity.Alojamiento;
import com.ict.commons.models.ReporteGraficosModel;

public interface IAlojamientoService {

	public Alojamiento guardar(Alojamiento entidad);

	public Alojamiento obtenerPorNombre(String nombre);

	public List<Alojamiento> listado();
	
	public List<AlojamientoModel> listadoClase();

	public List<Alojamiento> listadoPorPadre(Long padre);
	
	public List<ReporteGraficosModel> obtenerDatosGrafico();
}
