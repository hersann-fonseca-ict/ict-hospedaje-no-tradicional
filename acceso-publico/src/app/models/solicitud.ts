export class Solicitud {
	public id: number;
	public observaciones: string;
	public nombreComercial: string;
	public identificacionComercial: number;
	public identificacionRepresentante: number;
	public nombreRepresentante: string;
	public apellido1Representante: string;
	public apellido2Representante: string;
	public tipoServicio: number;
	public codigoTipoServicio: string;
	public telefono: string;
	public provincia: string;
	public canton: string;
	public distrito: string;
	public direccion: string;
	public estadoSolicitud: string = '';
	public fechaCreacion: Date;
	public beneficios: Beneficio[];
	public justificacion: string;;
}

export enum EstadoSolicitud {
	ABIERTA, APROBADA, RECHAZADA
}

export class Beneficio {
	public id: number;
	public nombreBeneficio: string;
	public activo: boolean;
	public fechaCreacion: Date;
}