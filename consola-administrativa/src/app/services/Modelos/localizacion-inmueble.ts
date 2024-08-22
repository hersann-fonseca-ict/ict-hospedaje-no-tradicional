import { Perfil } from "../Perfiles/perfil";
import { Alojamiento } from "./alojamiento";

export class LocalizacionInmueble {
    public id: number;
	public provincia: number;
	public canton: number;
	public distrito: number;
	public direccion: string;
	public numHabitacion: number;
	public numMaxHuesped: number;
	public numCamas: number;
	public urlInmueble1: string;
	public urlInmueble2: string;
	public urlInmueble3: string;
	public correo: string;
	public telefono: string;
	public perfil : Perfil;
	public alojamiento: Alojamiento;
	public codigo: string;
	public fechaAprobacion: Date;
	public fechaDesafiliacion: Date;
	public aprobar: boolean;
	public desafiliar: boolean;
	public rechazar: boolean;
	public fechaRechazo: Date;
	public motivoRechazo: string;
	public pendienteDesafiliar: boolean;
	public motivoDesafiliacion: string;
    public editable: boolean;
}
