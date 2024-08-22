package com.ict.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "alojamiento")
public class Alojamiento implements Serializable {

	private static final long serialVersionUID = 1L;

	public Alojamiento() {
		super();
	}

	public Alojamiento(Long id, String nombre, String descripcion, Integer nivel, Long padre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.padre = padre;
	}

	@Id
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	@Column(name = "id_alojamiento", nullable = false, unique = true)
	private Long id;

	@ApiModelProperty(required = true, example = "Apartamento", value = "Nombre del alojamiento")
	@Column(name = "nombre", nullable = false, length = 40)
	private String nombre;

	@ApiModelProperty(required = false, example = "Los apartamentos son viviendas....", value = "Descripcion del alojamiento")
	@Column(name = "descripcion", nullable = true, length = 60)
	private String descripcion;

	@ApiModelProperty(required = true, example = "0", value = "Nivel del alojamiento")
	@Column(name = "nivel", nullable = false)
	private Integer nivel;

	@ApiModelProperty(required = false, example = "0", value = "Padre del alojamiento")
	@Column(name = "padre", nullable = true)
	private Long padre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Long getPadre() {
		return padre;
	}

	public void setPadre(Long padre) {
		this.padre = padre;
	}

}
