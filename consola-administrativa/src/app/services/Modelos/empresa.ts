export class Empresa {
    public id: number;
    public razonSocial: string;
    public pais: string;
    public estado: string;
    public ciudad: string;
    public codigoPostal: string;
    public otrasSenas: string;
    public nombreRepresentante: string;
    public primerApe: string;
    public segundoApe: string;
    public correo: string;
    public url: string;
    public telefono: string;
    public fechaAprobacion: Date;
	public fechaDesafiliacion: Date;
	public aprobar: boolean;
	public desafiliar: boolean;
	public rechazar: boolean;
	public fechaRechazo: Date;
	public motivoRechazo: string;
	public pendienteDesafiliar: boolean;
	public motivoDesafiliacion: string;
}
