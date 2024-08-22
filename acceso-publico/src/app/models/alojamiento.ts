export class Alojamiento {
    public id: number;
    public nombre: string;
    public descripcion: string;
    public nivel: number;
    public padre: number;
    public children: Alojamiento[];
}