export class OpcionMenuModel {

    
    titulo: String;
    padre: boolean;
    ruta: string;
    opcionTurno: boolean;
    id: number;
    clase: String;
    fechaCreacion: Date;
    fk_opcion: number;
    lista_opciones: Array<OpcionMenuModel>;
    constructor() {
        this.titulo = "";
        this.padre = false;
        this.ruta = "";
        this.opcionTurno = false;
        this.id = 0;
        this.clase = "";
        this.fechaCreacion = new Date();
        this.fk_opcion = 0;
        this.lista_opciones = Array<OpcionMenuModel>();

    }
}
