package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "PERFILES_EMPRESA")
public class PerfilesEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;

	public PerfilesEmpresa() {
		super();
	}

	@EmbeddedId
	private PerfilesId id;

	@Column(name = "codigo_tipo_servicio", length = 20, nullable = true)
	@ApiModelProperty(required = false, example = "542983", value = "Codigo de servicio cuando sea requerido")
	private String codigoTipoServicio;


	@Column(name = "observaciones", nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "Esto es una observación", value = "observaciones")
	private String observaciones;

	@Column(name = "tipo_identificacion", nullable = true)
	@ApiModelProperty(required = false, example = "DIMEX", value = "Tipo de Identificacion")
	private String tipoIdentificacion;

	@Column(name = "identificacion", nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "3101445566", value = "Numero de cedula sin guiones")
	private String identificacion;

	@Column(name = "cedula_juridica", nullable = true)
	@ApiModelProperty(required = false, example = "3101290202", value = "Tipo de Identificacion")
	private String cedulaJuridica;

	@Column(name = "direccion", nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "200 mts norte de la iglesia", value = "Direccion")
	private String direccion;

	@Column(name = "nombre_provincia", nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre de la provincia")
	private String provincia;

	@Column(name = "nombre_canton", nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre del canton")
	private String canton;

	@Column(name = "nombre_distrito", nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre del distrito")
	private String distrito;

	@Column(name = "ID_EMPRESA", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long idEmpresa;

	@Column(name = "NOM_REPRESENTANTE_EMPRESA", nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "Patitos", value = "Nombre del representante legal")
	private String nombreRepresentanteEmpresa;

	@Column(name = "PRIMER_APE_EMPRESA", nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "Patitos", value = "Primer apellido")
	private String primerApeEmpresa;

	@Column(name = "SEGUNDO_APE_EMPRESA", nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "Patitos", value = "Segundo apellido")
	private String segundoApeEmpresa;

	@Column(name = "CORREO_NOTIFICACIONES", nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "correo@correo.com", value = "Correo electronico")
	private String correoNotificaciones;

	@Column(name = "TELEFONO_EMPRESA", nullable = true, length = 30)
	@ApiModelProperty(required = false, example = "506 23423444", value = "Numero de telefono")
	private String telefonoEmpresa;

	@Column(name = "URL_EMPRESA", nullable = true, length = 80)
	@ApiModelProperty(required = false, example = "empresa.com", value = "Url del sitio web")
	private String urlEmpresa;

	@Column(name = "RAZON_SOCIAL_EMPRESA", nullable = true, length = 80)
	@ApiModelProperty(required = false, example = "Patitos", value = "Nombre razon social")
	private String razonSocialEmpresa;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_aprobacion_empresa", nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de aprobacion")
	private Date fechaAprobacionEmpresa;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_desafiliacion_empresa", nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de desafiliacion")
	private Date fechaDesafiliacionEmpresa;

	@Column(name = "aprobado_empresa", nullable = true)
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no aprobado")
	private Boolean aprobarEmpresa;

	@Column(name = "desafiliado_empresa", nullable = true)
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no desafiliado")
	private Boolean desafiliarEmpresa;

	@Column(name = "rechazado_empresa", nullable = true)
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no rechazado")
	private Boolean rechazarEmpresa;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_rechazo_empresa", nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de rechazo")
	private Date fechaRechazoEmpresa;

	@Column(name = "motivo_rechazo_empresa", nullable = true, length = 500)
	@ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de rechazo")
	private String motivoRechazoEmpresa;

	@Column(name = "identificador_fisico_juridico", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "fisico", value = "tipo de empresa fisica o juridica")
	private String IdentificadorFisicoJuridico;

	public PerfilesId getId() {
		return id;
	}

	public void setId(PerfilesId id) {
		this.id = id;
	}

	public String getCodigoTipoServicio() {
		return codigoTipoServicio;
	}

	public void setCodigoTipoServicio(String codigoTipoServicio) {
		this.codigoTipoServicio = codigoTipoServicio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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

	public String getCedulaJuridica() {
		return cedulaJuridica;
	}

	public void setCedulaJuridica(String cedulaJuridica) {
		this.cedulaJuridica = cedulaJuridica;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreRepresentanteEmpresa() {
		return nombreRepresentanteEmpresa;
	}

	public void setNombreRepresentanteEmpresa(String nombreRepresentanteEmpresa) {
		this.nombreRepresentanteEmpresa = nombreRepresentanteEmpresa;
	}

	public String getPrimerApeEmpresa() {
		return primerApeEmpresa;
	}

	public void setPrimerApeEmpresa(String primerApeEmpresa) {
		this.primerApeEmpresa = primerApeEmpresa;
	}

	public String getSegundoApeEmpresa() {
		return segundoApeEmpresa;
	}

	public void setSegundoApeEmpresa(String segundoApeEmpresa) {
		this.segundoApeEmpresa = segundoApeEmpresa;
	}

	public String getCorreoNotificaciones() {
		return correoNotificaciones;
	}

	public void setCorreoNotificaciones(String correoNotificaciones) {
		this.correoNotificaciones = correoNotificaciones;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public String getUrlEmpresa() {
		return urlEmpresa;
	}

	public void setUrlEmpresa(String urlEmpresa) {
		this.urlEmpresa = urlEmpresa;
	}

	public String getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}

	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}

	public Date getFechaAprobacionEmpresa() {
		return fechaAprobacionEmpresa;
	}

	public void setFechaAprobacionEmpresa(Date fechaAprobacionEmpresa) {
		this.fechaAprobacionEmpresa = fechaAprobacionEmpresa;
	}

	public Date getFechaDesafiliacionEmpresa() {
		return fechaDesafiliacionEmpresa;
	}

	public void setFechaDesafiliacionEmpresa(Date fechaDesafiliacionEmpresa) {
		this.fechaDesafiliacionEmpresa = fechaDesafiliacionEmpresa;
	}

	public Boolean getAprobarEmpresa() {
		return aprobarEmpresa;
	}

	public void setAprobarEmpresa(Boolean aprobarEmpresa) {
		this.aprobarEmpresa = aprobarEmpresa;
	}

	public Boolean getDesafiliarEmpresa() {
		return desafiliarEmpresa;
	}

	public void setDesafiliarEmpresa(Boolean desafiliarEmpresa) {
		this.desafiliarEmpresa = desafiliarEmpresa;
	}

	public Boolean getRechazarEmpresa() {
		return rechazarEmpresa;
	}

	public void setRechazarEmpresa(Boolean rechazarEmpresa) {
		this.rechazarEmpresa = rechazarEmpresa;
	}

	public Date getFechaRechazoEmpresa() {
		return fechaRechazoEmpresa;
	}

	public void setFechaRechazoEmpresa(Date fechaRechazoEmpresa) {
		this.fechaRechazoEmpresa = fechaRechazoEmpresa;
	}

	public String getMotivoRechazoEmpresa() {
		return motivoRechazoEmpresa;
	}

	public void setMotivoRechazoEmpresa(String motivoRechazoEmpresa) {
		this.motivoRechazoEmpresa = motivoRechazoEmpresa;
	}

	public String getIdentificadorFisicoJuridico() {
		return IdentificadorFisicoJuridico;
	}

	public void setIdentificadorFisicoJuridico(String identificadorFisicoJuridico) {
		IdentificadorFisicoJuridico = identificadorFisicoJuridico;
	}

}
