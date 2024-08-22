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
@Table(name = "roles_sistema")
public class RolesSistema implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public RolesSistema() {
		
	}
	
	public RolesSistema(Long id, String codigo, String nombre, boolean activo, Date fechaCreacion) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
	}
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_roles_sistema", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;
	
	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "ADMIN", value = "Codigo usado por el api para designar accesos")
	private String codigo;
	
	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Administrador", value = "Nombre del rol")
	private String nombre;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "true/false", value = "Indica si esta activo o no")
	private boolean activo;
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
