export class PersonaFisica {
    public id?: number;
    public tipoIdentificacionFisica: string;
    public identificacion?: string;
    public nombre?: string;
    public apellido1?: string;
    public apellido2?: string;
    public fechaCreacion?: Date;
    public domicilio?: string;

    constructor(tipoIdentificacionFisica: string){
        this.id = undefined;
        this.tipoIdentificacionFisica = tipoIdentificacionFisica;
        this.identificacion = undefined;
        this.nombre = undefined;
        this.apellido1 = undefined;
        this.apellido2 = undefined;
        this.fechaCreacion = undefined;
        this.domicilio = undefined;
    }
}