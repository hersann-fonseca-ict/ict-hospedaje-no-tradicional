package com.ict.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ESTABLECIMIENTO_POR_ANALISTA")
public class EstablecimientosPorAnalista implements Serializable {

	private static final long serialVersionUID = 1L;

	public EstablecimientosPorAnalista() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq", sequenceName = "ID_ESTABLECIMIENTO_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "ID_ESTABLECIMIENTO", nullable = true, unique = true)
	@ApiModelProperty(required = false, example = "null", value = "Identificador secuencial")
	private Long id;

	@OneToOne
	@JoinColumn(name = "usuario_sistema_id", referencedColumnName = "id_usuario_sistema")
	@ApiModelProperty(required = false, value = "Usuario")
	private UsuarioSistema usuarioSistema;

	@OneToOne
	@JoinColumn(name = "loc_inmueble_id", referencedColumnName = "id_loc_inmueble")
	@ApiModelProperty(required = false, value = "Inmueble")
	private LocalizacionInmueble localizacion;

	@OneToOne
	@JoinColumn(name = "empresa_id", referencedColumnName = "id_empresa")
	@ApiModelProperty(required = false, value = "Empresa")
	private Empresa empresa;

	@OneToOne
	@JoinColumn(name = "jefe_id", referencedColumnName = "id_usuario_sistema")
	@ApiModelProperty(required = false, value = "Jefe")
	private UsuarioSistema jefe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public LocalizacionInmueble getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LocalizacionInmueble localizacion) {
		this.localizacion = localizacion;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public UsuarioSistema getJefe() {
		return jefe;
	}

	public void setJefe(UsuarioSistema jefe) {
		this.jefe = jefe;
	}

}
