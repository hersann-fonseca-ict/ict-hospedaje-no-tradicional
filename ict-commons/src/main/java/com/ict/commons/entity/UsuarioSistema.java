package com.ict.commons.entity;

import java.io.Serializable;
import javax.persistence.JoinColumn;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "usuarios_sistema")
@ApiModel(value = "Usuario",  description = "Representa un usuario en el sistema")
public class UsuarioSistema implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public UsuarioSistema() {
		
	}
	public UsuarioSistema(Long id, String nombreUsuario, String nombre, String clave, boolean activo, String correo,
			String ultimoCodigoVerificacion, Date fechaCreacion, List<RolesSistema> roles,String codigoAcceso) {
		
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.nombre = nombre;
		this.clave = clave;
		this.activo = activo;
		this.correo = correo;
		this.ultimoCodigoVerificacion = ultimoCodigoVerificacion;
		this.fechaCreacion = fechaCreacion;
		this.roles = roles;
		this.codigoAcceso = codigoAcceso;
	}
	@Id 
	@Column(name = "id_usuario_sistema", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;
	
	@Column(name = "nombre_usuario", nullable = false, length = 80, unique = true)
	@ApiModelProperty(required = true, example = "nombre.apellido", value = "Nombre de usuario usado para autenticarse en el sistema")
	private String nombreUsuario;
	
	@Column(name = "nombre", nullable = true, length = 200)
	@ApiModelProperty(required = true, example = "nombre apellido", value = "Nombre del usuario")
	private String nombre;
	
	@Column(nullable = true, length = 1000)
	@ApiModelProperty(required = false, example = "CalveMySegura123456789", value = "Clave almacenada usando BCrypt")
	private String clave;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "true/false", value = "Indica si el usuario esta activo o no")
	private boolean activo;
	
	@Column(nullable = false, length = 150)
	@ApiModelProperty(required = true, example = "nombre.apellido@dominio.com", value = "Correo al que le llegan notificaciones")
	private String correo;
	
	@Column(nullable = true, name = "ultimo_codigo_verificacion", length = 100)
	@ApiModelProperty(required = false, example = "asd3asd3ghe5y65", value = "Codigo que genera una validacion de 2 factores para generar la clave")
	private String ultimoCodigoVerificacion;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "fecha_creacion")
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;
	
	@ManyToMany
	@JoinTable(
			  name = "usuarios_sistema_roles",
			  joinColumns = @JoinColumn(name = "id_usuario"), 
			  inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private List<RolesSistema> roles;
	
	@Column(nullable = true, name = "codigo_acceso", length = 1000)
	@ApiModelProperty(required = false, example = "asd3asd3ghe5y65", value = "Codigo de la autenticacion de correo en consola")
	private String codigoAcceso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public List<RolesSistema> getRoles() {
		return roles;
	}
	public void setRoles(List<RolesSistema> roles) {
		this.roles = roles;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getUltimoCodigoVerificacion() {
		return ultimoCodigoVerificacion;
	}
	public void setUltimoCodigoVerificacion(String ultimoCodigoVerificacion) {
		this.ultimoCodigoVerificacion = ultimoCodigoVerificacion;
	}
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	 
}
