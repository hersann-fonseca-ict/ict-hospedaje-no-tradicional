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
@Table(name = "provincia")
@ApiModel(value = "Provincias", description = "Representa los catalogos de provincia")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	public Provincia(Long id, String nombre, Date fechaCreacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
	}

	public Provincia() {

	}

	@Id
	@ApiModelProperty(required = false, example = "1", value = "Identificador")
	@Column(name = "id_provincia", nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre de la provincia")
	private String nombre;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;
	@Column(nullable = true, length = 3)
	@ApiModelProperty(required = true, example = "01", value = "Código de la provincia")
	private String codProvincia;

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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
}
