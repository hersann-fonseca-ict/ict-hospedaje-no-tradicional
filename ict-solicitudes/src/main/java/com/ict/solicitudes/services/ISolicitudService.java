package com.ict.solicitudes.services;

import java.util.List;

import com.ict.commons.entity.Solicitud;
import com.ict.solicitudes.models.SolicitudesPaginados;

public interface ISolicitudService {

	public Solicitud guardar(Solicitud solicitud);

	public Solicitud obtenerPorId(Long id);

	public List<Solicitud> obtener();

	public SolicitudesPaginados obtenerPaginado(int pagina, int tamano, String columna);

	public Long obtenerProxId();

}
