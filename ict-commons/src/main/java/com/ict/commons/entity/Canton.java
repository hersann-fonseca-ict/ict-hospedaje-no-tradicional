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
@Table(name = "canton")
@ApiModel(value = "Canton", description = "Representa los catalogos de canton")
public class Canton implements Serializable {

	private static final long serialVersionUID = 1L;

	public Canton() {

	}

	public Canton(Long id, Long idProvincia, String nombre, Date fechaCreacion) {
		super();
		this.id = id;
		this.idProvincia = idProvincia;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
	}

	@Id
	@ApiModelProperty(required = false, example = "1", value = "Identificador")
	@Column(name = "id_canton", nullable = false, unique = true)
	private Long id;

	@Column(name = "id_provincia", nullable = false)
	@ApiModelProperty(required = true, example = "1", value = "ID Provinca")
	private Long idProvincia;

	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre del canton")
	private String nombre;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;
	
	@Column(nullable = true, length = 3)
	@ApiModelProperty(required = true, example = "01", value = "Código del cantón")
	private String codCanton;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
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

	public String getCodCanton() {
		return codCanton;
	}

	public void setCodCanton(String codCanton) {
		this.codCanton = codCanton;
	}
}
