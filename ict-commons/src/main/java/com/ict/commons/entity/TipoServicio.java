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
@Table(name = "tipo_servicio")
@ApiModel(value = "Tipo de servicio",  description = "Representa un tipo de servicio asignado a un perfil")
public class TipoServicio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public TipoServicio() {
		
	}
	
	public TipoServicio(Long id, String nombre, Boolean necesitaCodigo, Date fechaCreacion, String codigo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.necesitaCodigo = necesitaCodigo;
		this.fechaCreacion = fechaCreacion;
		this.codigo = codigo;
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_servicio")
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;
	
	@Column(length = 20, unique = true, nullable = false)
	@ApiModelProperty(required = true, example = "AirBnB", value = "Nombre de el tipo de servicio")
	private String nombre;
	
	@Column(nullable = false, name = "necesita_codigo")
	@ApiModelProperty(required = true, example = "true/false", value = "Indica si necesita un codigo de referencia como el codigo de AirBnB")
	private Boolean necesitaCodigo;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "fecha_creacion")
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;
	
	@Column(length = 10, nullable = false)
	@ApiModelProperty(required = true, example = "HNT", value = "Código del tipo de servicio")
	private String codigo;

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

	public Boolean isNecesitaCodigo() {
		return necesitaCodigo;
	}

	public void setNecesitaCodigo(Boolean necesitaCodigo) {
		this.necesitaCodigo = necesitaCodigo;
	}

	/*public Set<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(Set<Perfil> perfil) {
		this.perfil = perfil;
	}*/

	public Boolean getNecesitaCodigo() {
		return necesitaCodigo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
}
