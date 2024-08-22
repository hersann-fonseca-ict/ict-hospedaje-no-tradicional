package com.ict.commons.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "rol_sistema_opcion")
@ApiModel(value = "RolSistemaOpcion",  description = "Ligue entre Roles de sistema y Opciones de menu")
public class RolSistemaOpcion  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public RolSistemaOpcion() {

	}

	public RolSistemaOpcion(Long id, Long fk_opcion, Long id_rol) {
		super();
		this.id = id;
	}
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_sistema_opcion", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;
	
	
	@OneToOne()
	@JoinColumn(name = "fk_opcion",referencedColumnName = "id_opcion")
	private OpcionesMenu opciones_menu;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_rol" ,referencedColumnName = "id_roles_sistema")	 
	private RolesSistema roles_sistema;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public OpcionesMenu getOpciones_menu() {
		return opciones_menu;
	}
	
	public void setOpciones_menu(OpcionesMenu opciones_menu) {
		this.opciones_menu = opciones_menu;
	}
	
	public RolesSistema getRoles_sistema() {
		return roles_sistema;
	}
	
	public void setRoles_sistema(RolesSistema roles_sistema) {
		this.roles_sistema = roles_sistema;
	}



	
}
