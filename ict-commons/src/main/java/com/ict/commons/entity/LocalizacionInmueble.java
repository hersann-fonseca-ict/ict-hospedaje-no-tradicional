package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "localizacion")
public class LocalizacionInmueble implements Serializable {

	private static final long serialVersionUID = 1L;

	public LocalizacionInmueble() {
		super();
	}

	public LocalizacionInmueble(Long id, Provincia provincia, Canton canton, Distrito distrito, String direccion,
			Integer numHabitacion, Integer numMaxHuesped, Integer numCamas, String urlInmueble1, String urlInmueble2,
			String urlInmueble3, String correo, String telefono, Perfil perfil, Alojamiento alojamiento, String codigo,
			Date fechaAprobacion, Date fechaDesafiliacion, Boolean aprobar, Boolean desafiliar, Boolean rechazar,
			Date fechaRechazo, String motivoRechazo, Boolean pendienteDesafiliar, String motivoDesafiliacion, Boolean editable) {
		super();
		this.id = id;
		this.provincia = provincia;
		this.canton = canton;
		this.distrito = distrito;
		this.direccion = direccion;
		this.numHabitacion = numHabitacion;
		this.numMaxHuesped = numMaxHuesped;
		this.numCamas = numCamas;
		this.urlInmueble1 = urlInmueble1;
		this.urlInmueble2 = urlInmueble2;
		this.urlInmueble3 = urlInmueble3;
		this.correo = correo;
		this.telefono = telefono;
		this.perfil = perfil;
		this.alojamiento = alojamiento;
		this.codigo = codigo;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaDesafiliacion = fechaDesafiliacion;
		this.aprobar = aprobar;
		this.desafiliar = desafiliar;
		this.rechazar = rechazar;
		this.fechaRechazo = fechaRechazo;
		this.motivoRechazo = motivoRechazo;
		this.pendienteDesafiliar = pendienteDesafiliar;
		this.motivoDesafiliacion = motivoDesafiliacion;
		this.editable = editable;
	}

	@Id
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	@Column(name = "id_loc_inmueble", nullable = false, unique = true)
	private Long id;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "provincia", referencedColumnName = "id_provincia", nullable = false)
	@ApiModelProperty(required = true, example = "1", value = "Provincia")
	private Provincia provincia;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "canton", referencedColumnName = "id_canton", nullable = false)
	@ApiModelProperty(required = true, example = "1", value = "Canton")
	private Canton canton;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "distrito", referencedColumnName = "id_distrito", nullable = false)
	@ApiModelProperty(required = true, example = "1", value = "Distrito")
	private Distrito distrito;

	@Column(nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "200 mts norte de la iglesia", value = "Direccion")
	private String direccion;

	@Column(nullable = true)
	@ApiModelProperty(required = false, example = "10", value = "Numero de Habitaciones")
	private Integer numHabitacion;

	@Column(nullable = true)
	@ApiModelProperty(required = false, example = "10", value = "Numero Maximo de huespedes")
	private Integer numMaxHuesped;

	@Column(nullable = true)
	@ApiModelProperty(required = false, example = "10", value = "Numero de camas")
	private Integer numCamas;

	@Column(nullable = false, length = 50)
	@ApiModelProperty(required = true, example = "10", value = "Enlace web del inmueble")
	private String urlInmueble1;

	@Column(nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "10", value = "Enlace web del inmueble")
	private String urlInmueble2;

	@Column(nullable = true, length = 50)
	@ApiModelProperty(required = false, example = "10", value = "Enlace web del inmueble")
	private String urlInmueble3;

	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "prueba@gmail.com", value = "Correo para notificaciones")
	private String correo;

	@Column(nullable = true, length = 30)
	@ApiModelProperty(required = false, example = "506 23423444", value = "Numero de telefono")
	private String telefono;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil", nullable = true)
	@ApiModelProperty(required = true, example = "1", value = "ID perfil")
	private Perfil perfil;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "alojamiento_id", referencedColumnName = "id_alojamiento", nullable = true)
	@ApiModelProperty(required = false, value = "Alojamiento")
	private Alojamiento alojamiento;

	@Column(nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "HNT0000001", value = "Codigo localizacion inmueble")
	private String codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_aprobacion",nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de aprobacion")
	private Date fechaAprobacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_desafiliacion",nullable = true)
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_rechazo",nullable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de rechazo")
	private Date fechaRechazo;
	
	@Column(name = "motivo_rechazo", nullable = true, length = 500, columnDefinition = "varchar(500) default ''")
	@ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de rechazo")
	private String motivoRechazo;
	
	@Column(name = "pendiente_desafiliar", nullable = false, columnDefinition = "integer default 0")
	@ApiModelProperty(required = false, example = "True/False", value = "esta o no pendiente de desafiliar")
	private Boolean pendienteDesafiliar;
	
	@Column(name = "motivo_desafiliacion", nullable = true, length = 500, columnDefinition = "varchar(500) default ''")
	@ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de desafiliación")
	private String motivoDesafiliacion;

	@ApiModelProperty(required = false, example = "true", value = "Editable")
	@Column(name = "editable", nullable = false, columnDefinition = "integer default 0")
	private Boolean editable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(Integer numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public Integer getNumMaxHuesped() {
		return numMaxHuesped;
	}

	public void setNumMaxHuesped(Integer numMaxHuesped) {
		this.numMaxHuesped = numMaxHuesped;
	}

	public Integer getNumCamas() {
		return numCamas;
	}

	public void setNumCamas(Integer numCamas) {
		this.numCamas = numCamas;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getUrlInmueble1() {
		return urlInmueble1;
	}

	public void setUrlInmueble1(String urlInmueble1) {
		this.urlInmueble1 = urlInmueble1;
	}

	public String getUrlInmueble2() {
		return urlInmueble2;
	}

	public void setUrlInmueble2(String urlInmueble2) {
		this.urlInmueble2 = urlInmueble2;
	}

	public String getUrlInmueble3() {
		return urlInmueble3;
	}

	public void setUrlInmueble3(String urlInmueble3) {
		this.urlInmueble3 = urlInmueble3;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Date getFechaRechazo() {
		return fechaRechazo;
	}

	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
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

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

}
