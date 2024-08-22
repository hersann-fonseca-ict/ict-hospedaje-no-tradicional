export class Correo {
  public estado: string;
  public asunto: string;
  public cuerpo: string;
  public destino?: string;
  constructor(estado: string, asunto: string, cuerpo: string, destino: string | undefined = undefined) {
    this.estado = estado;
    this.asunto = asunto;
    this.cuerpo = cuerpo;
    this.destino = destino;
  }
}
