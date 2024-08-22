package com.ict.solicitudes.models;

import java.io.Serializable;

import com.ict.commons.entity.Solicitud;

public class SolicitudesPaginados implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Solicitud [] datos;
	private Pagina pagina;
	public Solicitud[] getDatos() {
		return datos;
	}
	public void setDatos(Solicitud[] datos) {
		this.datos = datos;
	}
	public Pagina getPagina() {
		return pagina;
	}
	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}
}
