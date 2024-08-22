package com.ict.commons.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "opciones_menu")
@ApiModel(value = "OpcionMenu",  description = "Representa una opcion del menu")
public class OpcionesMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public OpcionesMenu() {
		
	}
	
	public OpcionesMenu(Long id, Long fk_opcion, String ruta,String titulo, String icono,String clase, boolean padre,boolean activo, Date fechaCreacion, boolean opcion_turno  ) {
		super();
		this.id = id;
		this.fk_opcion = fk_opcion;
		this.ruta = ruta;
		this.activo = activo;
		this.titulo= titulo; 
		this.fechaCreacion=  fechaCreacion; 
		this.padre =  padre; 
		this.icono=  icono; 
		this.clase=  clase; 
		this.opcion_turno=  opcion_turno; 
		
		
		}
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_opcion", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	private Long id;
	

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fk_opcion", nullable = false, unique = true)
	@ApiModelProperty(required = false, example = "1")
	private Long fk_opcion;
	
	
	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "reporte-graficos", value = "Ruta de la opción de menu de reporte de gráficos")
	private String ruta;
	
	
	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "Reporte Gráficos", value = "Título de la opción de menú de reporte de gráficos")
	private String titulo;
	
	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "topic", value = "Icono que tiene la opción de menu")
	private String icono;
	
	@Column(nullable = false, length = 40)
	@ApiModelProperty(required = true, example = "topic", value = "Clase que tiene la opción de menu")
	private String clase;
	
	
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "true/false", value = "Indica si esta activa o no")
	private boolean activo;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "true/false", value = "Indica si es padre o no")
	private boolean padre;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "true/false", value = "Indica si es la opción de turno")
	private boolean opcion_turno;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getfk_opcion() {
		return fk_opcion;
	}
	public void setfk_opcion(Long fk_opcion) {
		this.fk_opcion = fk_opcion;
	}
	
	public String getclase() {
		return clase;
	}
	public void setclase(String clase) {
		this.clase	 = clase;
	}
	
	
	public String gettitulo() {
		return titulo;
	}
	public void settitulo(String titulo) {
		this.titulo	 = titulo;
	}
	
	public String geticono() {
		return icono;
	}
	public void seticono(String icono) {
		this.icono	 = icono;
	}
	
	
	public String getruta() {
		return ruta;
	}
	public void setruta(String ruta) {
		this.ruta = ruta;
	}

	
	public boolean getPadre() {
		return padre;
	}
	public void setPadre(boolean padre) {
		this.padre = padre;
	}
	
	public boolean getOpcionTurno() {
		return opcion_turno;
	}
	public void setOpcionTurno(boolean opcion_turno) {
		this.opcion_turno = opcion_turno;
	}
	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
