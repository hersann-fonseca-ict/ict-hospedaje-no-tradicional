package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "beneficio")
public class Beneficio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Beneficio() {
		super();
	}

	public Beneficio(Long id, String nombreBeneficio, Boolean activo, Date fechaCreacion) {
		super();
		this.id = id;
		this.nombreBeneficio = nombreBeneficio;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	@Column(name = "id_beneficio", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "nombre_beneficio", length = 80, nullable = false)
	@ApiModelProperty(required = true, example = "Exoneracion de impuestos", value = "Nombre del beneficio")
	private String nombreBeneficio;
	
	@ApiModelProperty(required = true, example = "true", value = "Indicador activo o inactivo")
	@Column(name = "activo", nullable = false)
	private Boolean activo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreBeneficio() {
		return nombreBeneficio;
	}

	public void setNombreBeneficio(String nombreBeneficio) {
		this.nombreBeneficio = nombreBeneficio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
