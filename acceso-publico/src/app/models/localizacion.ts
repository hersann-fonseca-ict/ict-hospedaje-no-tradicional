import { FormGroup } from "@angular/forms";
import { Provincia } from "./provincia";
import { Canton } from "./canton";
import { Distrito } from "./distrito";
import { Perfil } from "./perfiles";
import { Alojamiento } from "./alojamiento";

export class Localizacion {
    public id?: number;
    public provincia?: Provincia;
    public canton?: Canton;
    public distrito?: Distrito;
    public direccion?: string;
    public numHabitacion?: number;
    public numMaxHuesped?: number;
    public numCamas?: number;
    public urlInmueble1?: string;
    public urlInmueble2?: string;
    public urlInmueble3?: string;
    public correo?: string;
    public telefono?: string;
    public alojamiento?: Alojamiento;
    public perfil?: Perfil;
    public codigo?: string;
    public fechaAprobacion?: Date;
    public fechaDesafiliacion?: Date;
    public aprobar?: boolean;
    public desafiliar?: boolean;
    public pendienteDesafiliacion?: boolean;
    public rechazar?: boolean;
    public fechaRechazo?: Date;
    public motivoRechazo?: string;
    public motivoDesafiliacion?: string;
    public editable?: boolean;

    public editar: boolean = false;
    public form?: FormGroup = undefined;

    constructor() {
        this.perfil = undefined;
        this.codigo = undefined;
        this.fechaAprobacion = undefined;
        this.fechaDesafiliacion = undefined;
        this.fechaRechazo = undefined;
        this.aprobar = undefined;
        this.desafiliar = undefined;
        this.pendienteDesafiliacion = undefined;
        this.rechazar = undefined;
        this.motivoRechazo = undefined;
        this.motivoDesafiliacion = undefined;
        this.editable = true;
    }
}