package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated; 
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "solicitud")
@ApiModel(value = "Solicitud de beneficios",  description = "Representa una solicitud de beneficios")
public class Solicitud implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	public Solicitud() {
		
	}

	public Solicitud(Long id, String observaciones, String nombreComercial, Long identificacionComercial,
			Long identificacionRepresentante, String nombreRepresentante, String apellido1Representante,
			String apellido2Representante, Long tipoServicio, String codigoTipoServicio, String telefono,
			String provincia, String canton, String distrito, String direccion, List<Beneficio> beneficios,
			EstadoSolicitud estadoSolicitud, Date fechaCreacion, String justificacion) {
		super();
		this.id = id;
		this.observaciones = observaciones;
		this.nombreComercial = nombreComercial;
		this.identificacionComercial = identificacionComercial;
		this.identificacionRepresentante = identificacionRepresentante;
		this.nombreRepresentante = nombreRepresentante;
		this.apellido1Representante = apellido1Representante;
		this.apellido2Representante = apellido2Representante;
		this.tipoServicio = tipoServicio;
		this.codigoTipoServicio = codigoTipoServicio;
		this.telefono = telefono;
		this.provincia = provincia;
		this.canton = canton;
		this.distrito = distrito;
		this.direccion = direccion;
		this.beneficios = beneficios;
		this.estadoSolicitud = estadoSolicitud;
		this.fechaCreacion = fechaCreacion;
		this.justificacion = justificacion;
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitud", unique = true)
	private Long id;
	
	@Column(name = "observaciones", length = 255, nullable = true)
	@ApiModelProperty(required = false, example = "Necesito el beneficio por xxxxxx", value = "Observaciones")
	private String observaciones;
	
	@Column(length = 80, name = "nombre_comercial", nullable = true)
	@ApiModelProperty(required = false, example = "GBSYS S.A.", value = "Nombre comercial")
	private String nombreComercial;
	
	@Column(name = "identificacion_comercial", nullable = true)
	@ApiModelProperty(required = false, example = "3101445566", value = "Numero de cedula juridica sin guiones")
	private Long identificacionComercial;
	
	@Column(name = "identificacion_representante", nullable = false)
	@ApiModelProperty(required = true, example = "1222233334444", value = "Identificacion del representante legal sin guiones")
	private Long identificacionRepresentante;
	
	@Column(name = "nombre_representante", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Hector", value = "Nombre del representante legal")
	private String nombreRepresentante;
	
	@Column(name = "apellido_1_representante", nullable = false, length = 80)
	@ApiModelProperty(required = true, example = "Sanchez", value = "Primer apellido del representante legal")
	private String apellido1Representante;
	
	@Column(name = "apellido_2_representante", nullable = true, length = 80)
	@ApiModelProperty(required = true, example = "Ramirez", value = "Segundo apellido del representante legal")
	private String apellido2Representante;
	
	
	@JoinColumn(name="tipo_servicio_id", referencedColumnName = "id_tipo_servicio", insertable = false, updatable = false)
	@ApiModelProperty(required = true, example = "{id:1}", value = "Tipo de servicio")
	private Long tipoServicio;
	
	@Column(name = "codigo_tipo_servicio", length = 20, nullable = true)
	@ApiModelProperty(required = false, example = "542983", value = "Codigo de servicio cuando sea requerido")
	private String codigoTipoServicio;
	
	@Column(nullable = false, length = 20)
	@ApiModelProperty(required = true, example = "77664422", value = "Telefono")
	private String telefono;
	
	@Column(nullable = false, length = 20)
	@ApiModelProperty(required = true, example = "San Jose", value = "Nombre de la provincia")
	private String provincia;
	
	@Column(nullable = false, length = 20)
	@ApiModelProperty(required = true, example = "Escazu", value = "Nombre del canton")
	private String canton;
	
	@Column(nullable = false, length = 20)
	@ApiModelProperty(required = true, example = "San Rafael", value = "Nombre del distrito")
	private String distrito;
	
	@Column(nullable = false, length = 255)
	@ApiModelProperty(required = false, example = "200 mts norte de la iglesia", value = "Direccion")
	private String direccion;
	
	@Column(nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "rechazado por falta de infomación", value = "Justificacion")
	private String justificacion;

	@ManyToMany
	@JoinTable(
			  name = "perfil_beneficios",
			  joinColumns = @JoinColumn(name = "id_perfil"), 
			  inverseJoinColumns = @JoinColumn(name = "id_beneficio"))
	private List<Beneficio> beneficios;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "estado_solicitud", nullable = false)
    private EstadoSolicitud estadoSolicitud;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "fecha_creacion")
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public Long getIdentificacionComercial() {
		return identificacionComercial;
	}

	public void setIdentificacionComercial(Long identificacionComercial) {
		this.identificacionComercial = identificacionComercial;
	}

	public Long getIdentificacionRepresentante() {
		return identificacionRepresentante;
	}

	public void setIdentificacionRepresentante(Long identificacionRepresentante) {
		this.identificacionRepresentante = identificacionRepresentante;
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

	public Long getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(Long tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getCodigoTipoServicio() {
		return codigoTipoServicio;
	}

	public void setCodigoTipoServicio(String codigoTipoServicio) {
		this.codigoTipoServicio = codigoTipoServicio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Beneficio> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}

	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", observaciones=" + observaciones + ", nombreComercial=" + nombreComercial
				+ ", identificacionComercial=" + identificacionComercial + ", identificacionRepresentante="
				+ identificacionRepresentante + ", nombreRepresentante=" + nombreRepresentante
				+ ", apellido1Representante=" + apellido1Representante + ", apellido2Representante="
				+ apellido2Representante + ", tipoServicio=" + tipoServicio + ", codigoTipoServicio="
				+ codigoTipoServicio + ", telefono=" + telefono + ", provincia=" + provincia + ", canton=" + canton
				+ ", distrito=" + distrito + ", direccion=" + direccion + ", beneficios=" + beneficios
				+ ", estadoSolicitud=" + estadoSolicitud + ", fechaCreacion=" + fechaCreacion + "]";
	}
	
}
