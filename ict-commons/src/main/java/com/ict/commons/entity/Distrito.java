package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "distrito")
@ApiModel(value = "distrito", description = "Representa los catalogos de distrito")
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;

	public Distrito() {
		super();
	}

	public Distrito(Long id, Long idCanton, String nombre, Date fechaCreacion) {
		super();
		this.id = id;
		this.idCanton = idCanton;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
	}

	@Id
	@ApiModelProperty(required = false, example = "1", value = "Identificador")
	@Column(name = "id_distrito", nullable = false, unique = true)
	private Long id;

	@Column(name = "id_Canton", nullable = false)
	@ApiModelProperty(required = true, example = "1", value = "ID Canton")
	private Long idCanton;

	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre de la provincia")
	private String nombre;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;

	@Column(nullable = true, length = 3)
	@ApiModelProperty(required = true, example = "01", value = "Código del distrito")
	private String codDistrito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCanton() {
		return idCanton;
	}

	public void setIdCanton(Long idCanton) {
		this.idCanton = idCanton;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getcodDistrito() {
		return codDistrito;
	}

	public void setcodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}

}
