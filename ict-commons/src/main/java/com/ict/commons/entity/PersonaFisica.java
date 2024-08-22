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
@Table(name = "persona_fisica")
public class PersonaFisica implements Serializable {

	private static final long serialVersionUID = 1L;

	public PersonaFisica() {

	}

	public PersonaFisica(Long id, String identificacion, String tipoIdentificacionFisica, String nombre, String apellido1,
			String apellido2, Date fechaCreacion, String domicilio) {
		super();
		this.id = id;
		this.identificacion = identificacion;
		this.tipoIdentificacionFisica = tipoIdentificacionFisica;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaCreacion = fechaCreacion;
		this.domicilio = domicilio;
	}

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona_fisica", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;

	@Column(nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "3101445566", value = "Numero de cedula sin guiones")
	private String identificacion;

	@Column(name = "tipo_iden_fisica", nullable = true)
	@ApiModelProperty(required = false, example = "DIMEX", value = "Tipo de Identificacion")
	private String tipoIdentificacionFisica;

	@Column(nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Hector", value = "Nombre del representante legal")
	private String nombre;

	@Column(nullable = false, length = 80, name = "apellido_1")
	@ApiModelProperty(required = true, example = "Sanchez", value = "Primer apellido del representante legal")
	private String apellido1;

	@Column(nullable = true, length = 80, name = "apellido_2")
	@ApiModelProperty(required = true, example = "Ramirez", value = "Segundo apellido del representante legal")
	private String apellido2;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;

	@Column(nullable = true, length = 200, name = "domicilio")
	@ApiModelProperty(required = true, example = "Ramirez", value = "domicilio legal")
	private String domicilio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoIdentificacionFisica() {
		return tipoIdentificacionFisica;
	}

	public void setTipoIdentificacionFisica(String tipoIdentificacionFisica) {
		this.tipoIdentificacionFisica = tipoIdentificacionFisica;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

}
