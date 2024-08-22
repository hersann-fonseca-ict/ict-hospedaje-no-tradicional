package com.ict.beneficios.models;

import java.io.Serializable;

import com.ict.commons.entity.Beneficio;

public class BeneficiosPaginados implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Beneficio [] datos;
	private Pagina pagina;
	public Beneficio[] getDatos() {
		return datos;
	}
	public void setDatos(Beneficio[] datos) {
		this.datos = datos;
	}
	public Pagina getPagina() {
		return pagina;
	}
	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}
}
