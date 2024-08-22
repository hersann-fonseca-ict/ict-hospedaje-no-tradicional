package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;

	public Perfil() {

	}

	public Perfil(Long id, PersonaFisica personaFisica, PersonaJuridica personaJuridica, UsuarioSistema usuarioSistema,
			Empresa empresa, String codigoTipoServicio, List<Beneficio> beneficios, Date fechaCreacion,
			List<Solicitud> solicitud, String correo, String observaciones, TipoServicio tipoServicio) {
		super();
		this.id = id;
		this.personaFisica = personaFisica;
		this.personaJuridica = personaJuridica;
		this.usuarioSistema = usuarioSistema;
		this.empresa = empresa;
		this.codigoTipoServicio = codigoTipoServicio;
		this.beneficios = beneficios;
		this.fechaCreacion = fechaCreacion;
		this.solicitud = solicitud;
		this.correo = correo;;
		this.observaciones = observaciones;
		this.tipoServicio = tipoServicio;
	}

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	@Column(name = "id_perfil", nullable = false, unique = true)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "persona_fisica_id", referencedColumnName = "id_persona_fisica", nullable=true)
	@ApiModelProperty(required = false, value = "Persona Fisica")
	private PersonaFisica personaFisica;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "persona_juridica_id", referencedColumnName = "id_persona_juridica", nullable=true)
	@ApiModelProperty(required = false, value = "Persona Juridica")
	private PersonaJuridica personaJuridica;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "usu_sistema_id", referencedColumnName = "id_usuario_sistema")
	@ApiModelProperty(required = true, value = "Usuario de sistema")
	private UsuarioSistema usuarioSistema;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "empresa_id", referencedColumnName = "id_empresa", nullable=true)
	@ApiModelProperty(required = false, value = "Empresa")
	private Empresa empresa;

	@Column(name = "codigo_tipo_servicio", length = 20, nullable = true)
	@ApiModelProperty(required = false, example = "542983", value = "Codigo de servicio cuando sea requerido")
	private String codigoTipoServicio;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "tipo_servicio_id", referencedColumnName = "id_tipo_servicio")
	@ApiModelProperty(required = true, value = "TipoServicio")
	private TipoServicio tipoServicio;

	@ManyToMany
	@JoinTable(name = "perfil_beneficios", joinColumns = @JoinColumn(name = "id_perfil"), inverseJoinColumns = @JoinColumn(name = "id_beneficio"))
	private List<Beneficio> beneficios;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "perfil_solicitudes", joinColumns = @JoinColumn(name = "id_perfil"), inverseJoinColumns = @JoinColumn(name = "id_solicitud"))
	private List<Solicitud> solicitud;

	@Column(nullable = false, length = 50)
	@ApiModelProperty(required = true, example = "prueba@gmail.com", value = "Correo")
	private String correo;
	
	@Column(name = "observaciones", nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "Esto es una observación", value = "observación")
	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonaFisica getPersonaFisica() {
		return personaFisica;
	}

	public void setPersonaFisica(PersonaFisica personaFisica) {
		this.personaFisica = personaFisica;
	}

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCodigoTipoServicio() {
		return codigoTipoServicio;
	}

	public void setCodigoTipoServicio(String codigoTipoServicio) {
		this.codigoTipoServicio = codigoTipoServicio;
	} 

	public List<Beneficio> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<Solicitud> getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(List<Solicitud> solicitud) {
		this.solicitud = solicitud;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	
	
}
