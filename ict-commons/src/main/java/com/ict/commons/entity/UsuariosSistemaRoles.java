package com.ict.commons.entity;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity; 

import javax.persistence.Table;


import io.swagger.annotations.ApiModel;


@Entity
@Table(name = "usuarios_sistema_roles")
@ApiModel(value = "UsuarioSistemasRoles",  description = "Enlaza roles con usuarios")
public class UsuariosSistemaRoles {

	
		

	@EmbeddedId
	protected UsuariosSistemaRolesPK usuarioSistemaRolesPK;




	public UsuariosSistemaRolesPK getUsuarioSistemaRolesPK() {
		return usuarioSistemaRolesPK;
	}




	public void setUsuarioSistemaRolesPK(UsuariosSistemaRolesPK usuarioSistemaRolesPK) {
		this.usuarioSistemaRolesPK = usuarioSistemaRolesPK;
	} 
	

	
}
