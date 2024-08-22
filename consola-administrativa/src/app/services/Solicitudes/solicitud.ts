import { TipoServicio } from "../TipoServicio/tipo-servicio";
import { Beneficio } from "../Beneficios/beneficios";

export class Solicitud{

    public id: number;
    public observaciones: string;
	public nombreComercial: string;
	public identificacionComercial: number;
	public identificacionRepresentante: number;
	public nombreRepresentante: string;
	public apellido1Representante: string;
	public apellido2Representante: string;
	public tipoServicio: number;
    public codigoTipoServicio:string;
    public telefono: string;
	public provincia:string;
	public canton: string;
	public distrito: string;
	public direccion: string;
	public beneficios: Beneficio[];
    public estadoSolicitud: string;
	public fechaCreacion: Date;
    public justificacion: string;

}
export enum EstadoSolicitud {
	 ABIERTA, APROBADA, RECHAZADA
  }

