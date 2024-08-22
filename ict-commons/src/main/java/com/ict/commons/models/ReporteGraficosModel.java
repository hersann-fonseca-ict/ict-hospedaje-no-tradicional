package com.ict.commons.models;

public class ReporteGraficosModel {
	private String nombre;
	private Long valor;
	
	public ReporteGraficosModel(String nombre, Long valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	public ReporteGraficosModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}

	
}
