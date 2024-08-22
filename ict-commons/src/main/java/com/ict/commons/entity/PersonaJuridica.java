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
@Table(name = "persona_juridica")
public class PersonaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;

	public PersonaJuridica() {

	}

	public PersonaJuridica(Long id, String nombreComercial, String cedJuridico,String tipoIdentificacion, String identificacion, 
			String nombreRepresentante,String apellido1Representante, String apellido2Representante, Date fechaCreacion,
			String domicilio,String correo) {
		super();
		this.id = id;
		this.nombreComercial = nombreComercial;
		this.cedJuridico = cedJuridico;
	    this.tipoIdentificacion = tipoIdentificacion;
	    this.identificacion = identificacion;
		this.nombreRepresentante = nombreRepresentante;
		this.apellido1Representante = apellido1Representante;
		this.apellido2Representante = apellido2Representante;
		this.fechaCreacion = fechaCreacion;
		this.domicilio = domicilio;
		this.correo = correo;

	}

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona_juridica", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;

	@Column(length = 80, name = "nombre_comercial", nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "GBSYS S.A.", value = "Nombre comercial")
	private String nombreComercial;

	@Column(length = 20, name = "ced_juridica", nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "412333123", value = "cedula juridica")
	private String cedJuridico;

	@Column(name = "nombre_representante", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Hector", value = "Nombre del representante legal")
	private String nombreRepresentante;

	@Column(name = "apellido_1_representante", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Sanchez", value = "Primer apellido del representante legal")
	private String apellido1Representante;

	@Column(name = "apellido_2_representante", nullable = true, length = 80)
	@ApiModelProperty(required = true, example = "Ramirez", value = "Segundo apellido del representante legal")
	private String apellido2Representante;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;

	@Column(name = "domicilio", nullable = true, length = 200)
	@ApiModelProperty(required = true, example = "cartago", value = "Domicilio")
	private String domicilio;

	@Column(name = "correo", nullable = true, length = 80)
	@ApiModelProperty(required = true, example = "asdasdasdsad@asd.com", value = "correo")
	private String correo;
	
	@Column(name = "tipo_identificacion", nullable = true)
	@ApiModelProperty(required = false, example = "DIMEX", value = "Tipo de identificacion")
	private String tipoIdentificacion;
	
	@Column(nullable = true,  unique = true, name = "identificacion")
	@ApiModelProperty(required = false, example = "201540254", value = "Numero de cedula sin guiones")
	private String identificacion;

	public Long getId() {
		return id;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getApellido1Representante() {
		return apellido1Representante;
	}

	public void setApellido1Representante(String apellido1Representante) {
		this.apellido1Representante = apellido1Representante;
	}

	public String getApellido2Representante() {
		return apellido2Representante;
	}

	public void setApellido2Representante(String apellido2Representante) {
		this.apellido2Representante = apellido2Representante;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCedJuridico() {
		return cedJuridico;
	}

	public void setCedJuridico(String cedJuridico) {
		this.cedJuridico = cedJuridico;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
