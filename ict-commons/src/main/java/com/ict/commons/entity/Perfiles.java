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
@Table(name = "PERFILES")
public class Perfiles implements Serializable {
	private static final long serialVersionUID = 1L;

	public Perfiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EmbeddedId
	private PerfilesId id;

	@Column(name = "codigo_tipo_servicio", length = 20, nullable = true)
	@ApiModelProperty(required = false, example = "542983", value = "Codigo de servicio cuando sea requerido")
	private String codigoTipoServicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_aprobacion_inmueble", nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de aprobacion")
	private Date fechaAprobacionInmueble;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_desafiliacion_inmueble", nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de desafiliacion")
	private Date fechaDesafiliacionInmueble;

	@Column(name = "aprobado_inmueble", nullable = true)
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no aprobado")
	private Boolean aprobarInmueble;

	@Column(name = "desafiliado_inmueble", nullable = true)
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no desafiliado")
	private Boolean desafiliarInmueble;

	@Column(name = "observaciones", nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "Esto es una observación", value = "observaciones")
	private String observaciones;

	@Column(name = "rechazado_inmueble", nullable = true)
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no rechazado")
	private Boolean rechazarInmueble;

	@Column(name = "motivo_rechazo_inmueble", nullable = true, length = 500)
	@ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de rechazo")
	private String motivoRechazoInmueble;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_rechazo_inmueble", nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de rechazo")
	private Date fechaRechazoInmueble;

	@Column(name = "id_persona_fisica", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long idPersonaFisica;

	@Column(name = "identificacion", nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "3101445566", value = "Numero de cedula sin guiones")
	private String identificacionFisica;

	@Column(name = "tipo_iden_fisica", nullable = true)
	@ApiModelProperty(required = false, example = "DIMEX", value = "Tipo de Identificacion")
	private String tipoIdentificacionFisica;

	@Column(name = "nombre", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Hector", value = "Nombre del representante legal")
	private String nombre;

	@Column(nullable = false, length = 80, name = "apellido_1")
	@ApiModelProperty(required = true, example = "Sanchez", value = "Primer apellido del representante legal")
	private String apellido1;

	@Column(nullable = true, length = 80, name = "apellido_2")
	@ApiModelProperty(required = true, example = "Ramirez", value = "Segundo apellido del representante legal")
	private String apellido2;
	
	@Column(name = "domicilio_legal", nullable = true, length = 200)
	@ApiModelProperty(required = true, example = "200 mts norte de la iglesia", value = "Domicilio legal")
	private String domicilioLegal;

	@Column(name = "id_persona_juridica", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long idPersonaJuridica;

	@Column(length = 20, name = "ced_juridica", nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "412333123", value = "cedula juridica")
	private String cedJuridico;
	
	@Column(length = 20, name = "iden_representante", nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "412333123", value = "cedula representantelegal")
	private String idenRepresentante;

	@Column(length = 80, name = "nombre_comercial", nullable = false, unique = true)
	@ApiModelProperty(required = true, example = "GBSYS S.A.", value = "Nombre comercial")
	private String nombreComercial;

	@Column(name = "nombre_representante", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Hector", value = "Nombre del representante legal")
	private String nombreRepresentante;

	@Column(name = "apellido_1_representante", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Sanchez", value = "Primer apellido del representante legal")
	private String apellido1Representante;

	@Column(name = "apellido_2_representante", nullable = true, length = 80)
	@ApiModelProperty(required = true, example = "Ramirez", value = "Segundo apellido del representante legal")
	private String apellido2Representante;
	
	@Column(name = "domicilio_legal_representante", nullable = true, length = 200)
	@ApiModelProperty(required = true, example = "200 mts norte de la iglesia", value = "Domicilio legal del representante legal")
	private String domicilioLegalRepresentante;
	
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	@Column(name = "id_loc_inmueble", nullable = false, unique = true)
	private Long idLocalizacion;

	@Column(name = "direccion", nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "200 mts norte de la iglesia", value = "Direccion")
	private String direccionInmueble;
	
	@ApiModelProperty(required = false, example = "1", value = "Inumero habitaciones")
	@Column(name = "num_habitaciones", nullable = false, unique = true)
	private Long numHabitaciones;
	
	@ApiModelProperty(required = false, example = "1", value = "numero huespedes")
	@Column(name = "num_huespedes", nullable = false, unique = true)
	private Long numHuespedes;
	
	@Column(name = "telefono_inmueble", nullable = true, length = 30)
	@ApiModelProperty(required = false, example = "506 23423444", value = "Numero de telefono")
	private String telefonoInmueble;

	@Column(name = "correo_inmueble", nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "correo@correo.com", value = "Correo del inmueble")
	private String correoInmueble;

	@Column(name = "url_inmueble", nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "inmueble.com", value = "URL del inmueble")
	private String urlInmueble;

	@ApiModelProperty(required = false, example = "1", value = "Identificador")
	@Column(name = "id_provincia", nullable = false, unique = true)
	private Long idProvincia;

	@Column(name = "nombre_provincia", nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre de la provincia")
	private String provincia;

	@Column(name = "cod_provincia")
	@ApiModelProperty(required = false, example = "01", value = "Código de la provincia")
	private String codProvincia;

	@ApiModelProperty(required = false, example = "1", value = "Identificador")
	@Column(name = "id_canton", nullable = false, unique = true)
	private Long idCanton;

	@Column(name = "nombre_canton", nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre del canton")
	private String canton;

	@Column(name = "cod_canton")
	@ApiModelProperty(required = false, example = "01", value = "Código del cantón")
	private String codCanton;

	@Column(name = "id_distrito", nullable = false)
	@ApiModelProperty(required = false, example = "1", value = "Identificador")
	private Long idDistrito;

	@Column(name = "nombre_distrito", nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Cartago", value = "Nombre de la provincia")
	private String distrito;

	@Column(name = "cod_distrito")
	@ApiModelProperty(required = false, example = "01", value = "Código del distrito")
	private String codDistrito;

	public String getCodigoTipoServicio() {
		return codigoTipoServicio;
	}

	public PerfilesId getId() {
		return id;
	}

	public void setId(PerfilesId id) {
		this.id = id;
	}

	public void setCodigoTipoServicio(String codigoTipoServicio) {
		this.codigoTipoServicio = codigoTipoServicio;
	}

	public Date getFechaAprobacionInmueble() {
		return fechaAprobacionInmueble;
	}

	public void setFechaAprobacionInmueble(Date fechaAprobacionInmueble) {
		this.fechaAprobacionInmueble = fechaAprobacionInmueble;
	}

	public Date getFechaDesafiliacionInmueble() {
		return fechaDesafiliacionInmueble;
	}

	public void setFechaDesafiliacionInmueble(Date fechaDesafiliacionInmueble) {
		this.fechaDesafiliacionInmueble = fechaDesafiliacionInmueble;
	}

	public Boolean getAprobarInmueble() {
		return aprobarInmueble;
	}

	public void setAprobarInmueble(Boolean aprobarInmueble) {
		this.aprobarInmueble = aprobarInmueble;
	}

	public Boolean getDesafiliarInmueble() {
		return desafiliarInmueble;
	}

	public void setDesafiliarInmueble(Boolean desafiliarInmueble) {
		this.desafiliarInmueble = desafiliarInmueble;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getRechazarInmueble() {
		return rechazarInmueble;
	}

	public void setRechazarInmueble(Boolean rechazarInmueble) {
		this.rechazarInmueble = rechazarInmueble;
	}

	public Date getFechaRechazoInmueble() {
		return fechaRechazoInmueble;
	}

	public void setFechaRechazoInmueble(Date fechaRechazoInmueble) {
		this.fechaRechazoInmueble = fechaRechazoInmueble;
	}

	public Long getIdPersonaFisica() {
		return idPersonaFisica;
	}

	public void setIdPersonaFisica(Long idPersonaFisica) {
		this.idPersonaFisica = idPersonaFisica;
	}

	public String getIdentificacionFisica() {
		return identificacionFisica;
	}

	public void setIdentificacionFisica(String identificacionFisica) {
		this.identificacionFisica = identificacionFisica;
	}

	public String getTipoIdentificacionFisica() {
		return tipoIdentificacionFisica;
	}

	public void setTipoIdentificacionFisica(String tipoIdentificacionFisica) {
		this.tipoIdentificacionFisica = tipoIdentificacionFisica;
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
	
	public String getDomicilioLegal() {
		return domicilioLegal;
	}

	public void setDomicilioLegal(String domicilioLegal) {
		this.domicilioLegal = domicilioLegal;
	}

	public Long getIdPersonaJuridica() {
		return idPersonaJuridica;
	}

	public void setIdPersonaJuridica(Long idPersonaJuridica) {
		this.idPersonaJuridica = idPersonaJuridica;
	}

	public String getCedJuridico() {
		return cedJuridico;
	}

	public void setCedJuridico(String cedJuridico) {
		this.cedJuridico = cedJuridico;
	}
	
	public String getIdenRepresentante() {
		return idenRepresentante;
	}

	public void setIdenRepresentante(String idenRepresentante) {
		this.idenRepresentante = idenRepresentante;
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
	
	public String getDomicilioLegalRepresentante() {
		return domicilioLegalRepresentante;
	}

	public void setDomicilioLegalRepresentante(String domicilioLegalRepresentante) {
		this.domicilioLegalRepresentante = domicilioLegalRepresentante;
	}

	public Long getIdLocalizacion() {
		return idLocalizacion;
	}

	public void setIdLocalizacion(Long idLocalizacion) {
		this.idLocalizacion = idLocalizacion;
	}

	public String getDireccionInmueble() {
		return direccionInmueble;
	}

	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}
	
	public Long getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(Long numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public Long getNumHuespedes() {
		return numHuespedes;
	}

	public void setNumHuespedes(Long numHuespedes) {
		this.numHuespedes = numHuespedes;
	}

	public String getTelefonoInmueble() {
		return telefonoInmueble;
	}

	public String getCorreoInmueble() {
		return correoInmueble;
	}

	public void setCorreoInmueble(String correoInmueble) {
		this.correoInmueble = correoInmueble;
	}

	public String getUrlInmueble() {
		return urlInmueble;
	}

	public void setUrlInmueble(String urlInmueble) {
		this.urlInmueble = urlInmueble;
	}

	public void setTelefonoInmueble(String telefonoInmueble) {
		this.telefonoInmueble = telefonoInmueble;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public Long getIdCanton() {
		return idCanton;
	}

	public void setIdCanton(Long idCanton) {
		this.idCanton = idCanton;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getCodCanton() {
		return codCanton;
	}

	public void setCodCanton(String codCanton) {
		this.codCanton = codCanton;
	}

	public Long getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Long idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}

}
