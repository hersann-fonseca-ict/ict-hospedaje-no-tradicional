package com.ict.beneficios.models;

import java.io.Serializable;

public class Pagina implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int tamano;
	private int cantidadTotalElementos;
	private int cantidadTotalPaginas;
	private int paginaActual;
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	public int getCantidadTotalElementos() {
		return cantidadTotalElementos;
	}
	public void setCantidadTotalElementos(int cantidadTotalElementos) {
		this.cantidadTotalElementos = cantidadTotalElementos;
	}
	public int getCantidadTotalPaginas() {
		return cantidadTotalPaginas;
	}
	public void setCantidadTotalPaginas(int cantidadTotalPaginas) {
		this.cantidadTotalPaginas = cantidadTotalPaginas;
	}
	public int getPaginaActual() {
		return paginaActual;
	}
	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}
	
}
