package com.ict.perfiles.models;

public class PerfilExcelModel {

	public PerfilExcelModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerfilExcelModel(String tipo1, String tipo2, String cedula, String nombre1, String nombre2, String estado,
			String codigo, String fechaAprobacion, String fechaDesafiliacion, String provincia, String canton,
			String distrito, String direccion, String correo, String telefono, String correoNotificaciones,
			String telefonoEmpresa, String urlEmpresa, String cedulaJuridica, String identificadorFisicoJuridico,
			String identificacionRepresentante, String domicilioLegal, String numeroHabitaciones, String numeroHuespedes) {
		super();
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.cedula = cedula;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.estado = estado;
		this.codigo = codigo;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaDesafiliacion = fechaDesafiliacion;
		this.provincia = provincia;
		this.canton = canton;
		this.distrito = distrito;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.correoNotificaciones = correoNotificaciones;
		this.telefonoEmpresa = telefonoEmpresa;
		this.urlEmpresa = urlEmpresa;
		this.cedulaJuridica = cedulaJuridica;
		this.identificadorFisicoJuridico = identificadorFisicoJuridico;
		this.identificacionRepresentante = identificacionRepresentante;
		this.domicilioLegal = domicilioLegal;
		this.numeroHabitaciones = numeroHabitaciones;
		this.numeroHuespedes = numeroHuespedes;
	}

	/**
	 * Columna A del excel que representa el tipo de servicio
	 */
	private String tipo1;

	/**
	 * Columna A del excel que representa el tipo de servicio
	 */
	private String tipo2;

	/**
	 * Columna C del excel que representa la cedula ya sea fisica o juridica
	 */
	private String cedula;

	/**
	 * Columna D del excel que representa el nombre de la persona que se registra
	 * fisica o juridica
	 */
	private String nombre1;

	/**
	 * Columna E del excel que representa el nombre de la persona que se registra
	 * fisica, cuando es juridica nombre del representante legal
	 */
	private String nombre2;

	/**
	 * Columna F del excel que representa el estado siempre dice (En operación
	 * (Activo))
	 */
	private String estado;

	/**
	 * Columna G del excel que representa el código del inmueble
	 */
	private String codigo;

	/**
	 * Columna H del excel que representa la fecha de validacion de la informacion
	 */
	private String fechaAprobacion;

	/**
	 * Columna I del excel que representa la fecha de desafiliacion del usuario
	 */
	private String fechaDesafiliacion;

	/**
	 * Columna J del excel que representa la provinvia del inmueble
	 */
	private String provincia;

	/**
	 * Columna K del excel que representa el canton del inmueble
	 */
	private String canton;

	/**
	 * Columna L del excel que representa el distrito del inmueble
	 */
	private String distrito;

	/**
	 * Columna M del excel que representa la direccion del inmueble
	 */
	private String direccion;

	/**
	 * Columna N del excel que representa el correo del inmueble para notificaciones
	 */
	private String correo;

	/**
	 * Columna O del excel que representa el telefono de inmueble
	 */
	private String telefono;

	/**
	 * Columna P del excel que representa el correo para notificaciones de la
	 * empresa
	 */
	private String correoNotificaciones;

	/**
	 * Columna Q del excel que representa el telefono de la empresa
	 */
	private String telefonoEmpresa;

	/**
	 * Columna R del excel que representa el sitio web de la empresa
	 */
	private String urlEmpresa;

	/**
	 * Columna S del excel que representa la cedula juridica de la empresa
	 */
	private String cedulaJuridica;

	/**
	 * Columna T del excel que representa el tipo de empresa es fisico o juridico
	 */
	private String identificadorFisicoJuridico;
	
	/**
	 * Columna U del excel que representa la cedula del representante legal
	 */
	private String identificacionRepresentante;
	
	/**
	 * Columna V del excel que representa el domicilio legal de la persona 
	 */
	private String domicilioLegal;
	
	/**
	 * Columna V del excel que representa el numero de habitaciones del inmueble
	 */
	private String numeroHabitaciones;
	
	/**
	 * Columna V del excel que representa el numero de huespedes del inmueble
	 */
	private String numeroHuespedes;

	public String getTipo1() {
		return tipo1;
	}

	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}

	public String getTipo2() {
		return tipo2;
	}

	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getFechaDesafiliacion() {
		return fechaDesafiliacion;
	}

	public void setFechaDesafiliacion(String fechaDesafiliacion) {
		this.fechaDesafiliacion = fechaDesafiliacion;
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

	public String getIdentificacionRepresentante() {
		return identificacionRepresentante;
	}

	public void setIdentificacionRepresentante(String identificacionRepresentante) {
		this.identificacionRepresentante = identificacionRepresentante;
	}

	public String getDomicilioLegal() {
		return domicilioLegal;
	}

	public void setDomicilioLegal(String domicilioLegal) {
		this.domicilioLegal = domicilioLegal;
	}

	public String getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(String numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public String getNumeroHuespedes() {
		return numeroHuespedes;
	}

	public void setNumeroHuespedes(String numeroHuespedes) {
		this.numeroHuespedes = numeroHuespedes;
	}
	
}
