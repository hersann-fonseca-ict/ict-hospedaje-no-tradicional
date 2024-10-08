import { PerfilesId } from "./perfiles-id";

export class PerfilModel {
	public id: PerfilesId;
	public codigoTipoServicio: string;
	public fechaAprobacionInmueble: Date;
	public fechaDesafiliacionInmueble: Date;
	public aprobarInmueble: boolean;
	public desafiliarInmueble: boolean;
	public observaciones: string;
	public rechazarInmueble: boolean;
	public fechaRechazoInmueble: Date;
	public motivoRechazoInmueble: string;
	public idPersonaFisica: number;
	public identificacionFisica: string;
	public tipoIdentificacionFisica: string;
	public nombre: string;
	public apellido1: string;
	public apellido2: string;
	public idPersonaJuridica: number;
	public cedJuridico: string;
	public nombreComercial: string;
	public nombreRepresentante: string;
	public apellido1Representante: string;
	public apellido2Representante: string;
	public idLocalizacion: number;
	public direccionInmueble: string;
	public telefonoInmueble: string;
	public correoInmueble: string;
	public urlInmueble: string;
	public idProvincia: number;
	public provincia: string;
	public idCanton: number;
	public canton: string;
	public idDistrito: number;
	public distrito: string;
	public idEmpresa: number;
	public nombreRepresentanteEmpresa: string;
	public primerApeEmpresa: string;
	public segundoApeEmpresa: string;
	public correoNotificaciones: string;
	public telefonoEmpresa: string;
	public urlEmpresa: string;
	public razonSocialEmpresa: string;
	public fechaAprobacionEmpresa: Date;
	public fechaDesafiliacionEmpresa: Date;
	public fechaRechazoEmpresa: Date;
	public aprobarEmpresa: Boolean;
	public desafiliarEmpresa: Boolean;
	public rechazarEmpresa: Boolean;
	public motivoRechazoEmpresa: string;
}
