export class PerfilExcel {
	/**
	 * Columna A del excel que representa el tipo de servicio
	 */
	public tipo1: string;

	/**
	 * Columna A del excel que representa el tipo de servicio
	 */
	public tipo2: string;

	/**
	 * Columna C del excel que representa la cedula ya sea fisica o juridica
	 */
	public cedula: string;

	/**
	 * Columna D del excel que representa el nombre de la persona que se registra fisica o juridica
	 */
	public nombre1: string;

	/**
	 * Columna E del excel que representa el nombre de la persona que se registra fisica, cuando es juridica nombre del representante legal
	 */
	public nombre2: string;

	/**
	 * Columna F del excel que representa el estado siempre dice (En operación (Activo))
	 */
	public estado: string;

	/**
	 * Columna G del excel que representa el código del inmueble
	 */
	public codigo: string;

	/**
	 * Columna H del excel que representa la fecha de validacion de la informacion
	 */
	public fechaAprobacion: String;

	/**
	 * Columna I del excel que representa la fecha de desafiliacion del usuario
	 */
	public fechaDesafiliacion: String;

	/**
	 * Columna J del excel que representa la provinvia del inmueble
	 */
	public provincia: string;

	/**
	 * Columna K del excel que representa el canton del inmueble
	 */
	public canton: string;

	/**
	 * Columna L del excel que representa el distrito del inmueble
	 */
	public distrito: string;

	/**
	 * Columna M del excel que representa la direccion del inmueble
	 */
	public direccion: string;

	/**
	 * Columna N del excel que representa el correo del inmueble para notificaciones
	 */
	public correo: string;

	/**
	 * Columna O del excel que representa el telefono de inmueble
	 */
	public telefono: string;

	/**
	 * Columna P del excel que representa el correo de notificaciones de la empresa
	 */
	public correoNotificaciones: string;

	/**
	 * Columna Q del excel que representa el telefono de la empresa
	 */
	public telefonoEmpresa: String;

	/** 
	 * Columna R del escel que representa el URL del sitio de la empresa
	 */
	public urlEmpresa: string;

	/**
    * Columna S del excel que representa la cedula juridica de la empresa
    */
	public cedulaJuridica: string;

	 /**
	 * Columna T del excel que representa el tipo de empresa es fisico o juridico
	 */
	public identificadorFisicoJuridico: string;
}
