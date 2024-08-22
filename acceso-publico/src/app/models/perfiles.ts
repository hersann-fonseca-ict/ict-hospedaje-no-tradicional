import { PersonaFisica } from "./personaFisica";
import { PersonaJuridica } from "./personaJuridica";
import { UsuarioSistema } from "./usuario";
import { Solicitud } from "./solicitud";
import { Empresa } from './empresa';
import { TipoServicio } from "./tipo-servicio";

export class Perfil {
    public id?: number;
    public personaFisica?: PersonaFisica;
    public personaJuridica?: PersonaJuridica;
    public empresa?: Empresa;
    public usuarioSistema: UsuarioSistema;
    public codigoTipoServicio?: string;
    public fechaCreacion: Date;
    public solicitud?: Solicitud[];
    public correo?: string;
    public tipoServicio: TipoServicio;
    constructor(tipoServicio: TipoServicio, usuarioSistema: UsuarioSistema, correo?: string, fechaCreacion: Date = new Date(), id?: number) {
        this.id = id;
        this.usuarioSistema = usuarioSistema;
        this.codigoTipoServicio = tipoServicio.codigo;
        this.fechaCreacion = fechaCreacion;
        this.correo = correo;
        this.tipoServicio = tipoServicio;
        this.solicitud = undefined;
        this.personaJuridica = undefined;
        this.personaFisica = undefined;
        this.empresa = undefined;
    }
}