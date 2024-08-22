export class PersonaJuridica {
    public id?: number;
    public nombreComercial?: string;
    public cedJuridico?: string;
    public tipoIdentificacion?: string;
    public identificacion?: string;
    public nombreRepresentante?: string;
    public apellido1Representante?: string;
    public apellido2Representante?: string ;
    public fechaCreacion?: Date;
    public domicilio?: string;
    public correo?: string;
    
    constructor(){
        this.id = undefined;
        this.nombreComercial = undefined;
        this.cedJuridico = undefined;
        this.tipoIdentificacion = undefined;
        this.identificacion = undefined;
        this.nombreRepresentante = undefined;
        this.apellido1Representante = undefined;
        this.apellido2Representante = undefined;
        this.fechaCreacion = undefined;
        this.domicilio = undefined;
        this.correo = undefined;
    }
}