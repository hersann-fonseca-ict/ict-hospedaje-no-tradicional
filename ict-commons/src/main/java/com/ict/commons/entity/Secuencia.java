package com.ict.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "secuencia")
@ApiModel(value = "Secuencia", description = "Representa la secuencia para el codigo del perfil")
public class Secuencia implements Serializable {

	private static final long serialVersionUID = 1L;

	public Secuencia() {
		super();
	}
	 
	public Secuencia(String id, Long secuencia) {
		super();
		this.id = id;
		this.secuencia = secuencia;
	} 

	@Id
	@Column(name = "id_secuencia", length = 5, unique = true, nullable = false)
	@ApiModelProperty(required = true, example = "HTC", value = "Identificador del tipo de secuencia")
	private String id;

	@Column(name = "secuencia", nullable = true)
	@ApiModelProperty(required = false, example = "1", value = "Numero de la secuencia")
	private Long secuencia;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	
}
