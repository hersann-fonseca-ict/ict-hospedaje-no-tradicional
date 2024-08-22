export class TipoServicio {
    public id: number;
    public nombre: string;
    public necesitaCodigo: boolean;
    public fechaCreacion: Date;
    public codigo: string;
}

export class Actividades {
    public id: number;
    public tipoServicio: TipoServicio;
    public observacion: string;
}