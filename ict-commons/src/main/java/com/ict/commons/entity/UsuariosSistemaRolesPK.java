package com.ict.commons.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import io.swagger.annotations.ApiModelProperty;


@Embeddable
public class UsuariosSistemaRolesPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id_usuario;

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1")
	private Long id_rol;

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Long getId_rol() {
		return id_rol;
	}

	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}
	
}
