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
@Table(name = "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	public Empresa() {
		super();
	}

	public Empresa(Long id, String razonSocial, String pais, String estado, String ciudad, String codigoPostal,
			String otrasSenas, String nombreRepresentante, String primerApe, String segundoApe, String correo,
			String url, String telefono, Date fechaAprobacion, Date fechaDesafiliacion, Boolean aprobar,
			Boolean desafiliar, Boolean rechazar, String motivoRechazo, Date fechaRechazo, Boolean pendienteDesafiliar,
			String motivoDesafiliacion, String tipoIdentificacion, String identificacion, String distrito, String cedulaJuridica, String identificadorFisicoJuridico) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.pais = pais;
		this.estado = estado;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.otrasSenas = otrasSenas;
		this.nombreRepresentante = nombreRepresentante;
		this.primerApe = primerApe;
		this.segundoApe = segundoApe;
		this.correo = correo;
		this.url = url;
		this.telefono = telefono;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaDesafiliacion = fechaDesafiliacion;
		this.aprobar = aprobar;
		this.desafiliar = desafiliar;
		this.rechazar = rechazar;
		this.motivoRechazo = motivoRechazo;
		this.fechaRechazo = fechaRechazo;
		this.pendienteDesafiliar = pendienteDesafiliar;
		this.motivoDesafiliacion = motivoDesafiliacion;
	    this.tipoIdentificacion = tipoIdentificacion;
	    this.identificacion = identificacion;
	    this.distrito = distrito;
	    this.cedulaJuridica = cedulaJuridica;
	    this.identificadorFisicoJuridico = identificadorFisicoJuridico;
	}

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;

	@Column(nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Patitos", value = "Razon Social")
	private String razonSocial;

	@Column(nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Patitos", value = "pais")
	private String pais;

	@Column(nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Patitos", value = "estado")
	private String estado;

	@Column(nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Patitos", value = "ciudad")
	private String ciudad;

	@Column(nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Patitos", value = "Codigo Postal")
	private String codigoPostal;

	@Column(nullable = true, length = 200)
	@ApiModelProperty(required = false, example = "Patitos", value = "Otras Senas")
	private String otrasSenas;

	@Column(nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "Patitos", value = "Nombre del representante legal")
	private String nombreRepresentante;

	@Column(nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "Patitos", value = "Primer apellido")
	private String primerApe;

	@Column(nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "Patitos", value = "Segundo apellido")
	private String segundoApe;

	@Column(nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "Patitos", value = "Correo")
	private String correo;

	@Column(nullable = true, length = 80)
	@ApiModelProperty(required = false, example = "Patitos", value = "Direccion web")
	private String url;

	@Column(nullable = true, length = 30)
	@ApiModelProperty(required = false, example = "Patitos", value = "Telefono")
	private String telefono;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_aprobado",nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de aprobacion")
	private Date fechaAprobacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_desafiliado",nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de desafiliacion")
	private Date fechaDesafiliacion;
	
	@Column(name = "aprobado", nullable = false, columnDefinition = "integer default 0")
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no aprobado")
	private Boolean aprobar;
	
	@Column(name = "desafiliado", nullable = false, columnDefinition = "integer default 0")
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no desafiliado")
	private Boolean desafiliar;
	
	@Column(name = "rechazado", nullable = false, columnDefinition = "integer default 0")
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no rechazado")
	private Boolean rechazar;
	
	@Column(name = "motivo_rechazo", nullable = true, length = 500, columnDefinition = "varchar(500) default ''")
	@ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de rechazo")
	private String motivoRechazo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_rechazo",nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de rechazo")
	private Date fechaRechazo;
	
	@Column(name = "pendiente_desafiliar", nullable = false, columnDefinition = "integer default 0")
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no pendiente de desafiliar")
	private Boolean pendienteDesafiliar;
	
	@Column(name = "motivo_desafiliacion", nullable = true, length = 500, columnDefinition = "varchar(500) default ''")
	@ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de desafiliación")
	private String motivoDesafiliacion;
	
	@Column(name = "tipo_identificacion", nullable = true)
	@ApiModelProperty(required = false, example = "DIMEX", value = "Tipo de Identificacion")
	private String tipoIdentificacion;
	
	@Column(nullable = true, unique = true, name = "identificacion")
	@ApiModelProperty(required = false, example = "3101445566", value = "Numero de cedula sin guiones")
	private String identificacion;
	
	@Column(nullable = true, length = 80, name = "distrito")
	@ApiModelProperty(required = false, example = "Patitos", value = "distrito")
	private String distrito;
	
	@Column(nullable = true, length = 80, name = "cedula_juridica")
	@ApiModelProperty(required = false, example = "Patitos", value = "cedulaJuridica")
	private String cedulaJuridica;
	
	@Column(nullable = true, length = 80, name = "identificador_fisico_juridico")
	@ApiModelProperty(required = false, example = "Patitos", value = "identificadorFisicoJuridico")
	private String identificadorFisicoJuridico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getOtrasSenas() {
		return otrasSenas;
	}

	public void setOtrasSenas(String otrasSenas) {
		this.otrasSenas = otrasSenas;
	}

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getPrimerApe() {
		return primerApe;
	}

	public void setPrimerApe(String primerApe) {
		this.primerApe = primerApe;
	}

	public String getSegundoApe() {
		return segundoApe;
	}

	public void setSegundoApe(String segundoApe) {
		this.segundoApe = segundoApe;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public Date getFechaDesafiliacion() {
		return fechaDesafiliacion;
	}

	public void setFechaDesafiliacion(Date fechaDesafiliacion) {
		this.fechaDesafiliacion = fechaDesafiliacion;
	}

	public Boolean getAprobar() {
		return aprobar;
	}

	public void setAprobar(Boolean aprobar) {
		this.aprobar = aprobar;
	}

	public Boolean getDesafiliar() {
		return desafiliar;
	}

	public void setDesafiliar(Boolean desafiliar) {
		this.desafiliar = desafiliar;
	}

	public Boolean getRechazar() {
		return rechazar;
	}

	public void setRechazar(Boolean rechazar) {
		this.rechazar = rechazar;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public Date getFechaRechazo() {
		return fechaRechazo;
	}

	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}

	public Boolean getPendienteDesafiliar() {
		return pendienteDesafiliar;
	}

	public void setPendienteDesafiliar(Boolean pendienteDesafiliar) {
		this.pendienteDesafiliar = pendienteDesafiliar;
	}

	public String getMotivoDesafiliacion() {
		return motivoDesafiliacion;
	}

	public void setMotivoDesafiliacion(String motivoDesafiliacion) {
		this.motivoDesafiliacion = motivoDesafiliacion;
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

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getCedulaJuridica() {
		return cedulaJuridica;
	}

	public void setCedulaJuridica(String cedulaJuridica) {
		this.cedulaJuridica = cedulaJuridica;
	}

	public String getIdentificadorFisicoJuridico() {
		return identificadorFisicoJuridico;
	}

	public void setIdentificadorFisicoJuridico(String identificadorFisicoJuridico) {
		this.identificadorFisicoJuridico = identificadorFisicoJuridico;
	}

}
