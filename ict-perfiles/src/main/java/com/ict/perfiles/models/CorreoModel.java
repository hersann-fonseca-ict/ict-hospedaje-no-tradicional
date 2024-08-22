package com.ict.perfiles.models;

import java.io.Serializable;

public class CorreoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CorreoModel(String estado, String destino, String asunto, String cuerpo) {
		super();
		this.estado = estado;
		this.destino = destino;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}

	String estado;
	String destino;
	String asunto;
	String cuerpo;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
}
