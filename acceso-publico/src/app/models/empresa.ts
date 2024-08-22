export class Empresa {
    public id: number | null;
    public razonSocial: string | null;
    public pais: string | null;
    public estado: string | null;
    public ciudad: string | null;
    public codigoPostal: string | null;
    public otrasSenas: string | null;
    public nombreRepresentante: string | null;
    public primerApe: string | null;
    public segundoApe: string | null;
    public correo: string | null;
    public url: string | null;
    public telefono: string | null;
    public fechaAprobacion: Date;
	public fechaDesafiliacion: Date;
	public aprobar: boolean;
	public desafiliar: boolean;
	public rechazar: boolean;
	public fechaRechazo: Date;
	public motivoRechazo: string | null;
    public tipoIdentificacion: string;
    public identificacion: string;
    public cedulaJuridica: string | null;
    public distrito: string | null;
    public identificadorFisicoJuridico: string | null;
}