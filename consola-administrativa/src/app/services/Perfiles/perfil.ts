import { PersonaFisica } from "../Perfiles/personaFisica"
import { PersonaJuridica } from "../Perfiles/personaJuridica";
import { UsuarioSistema } from "../Usuarios/usuarios";
import { TipoServicio } from "../TipoServicio/tipo-servicio";
import { Beneficio } from "../Beneficios/beneficios";
import { Empresa } from "../Modelos/empresa";

export class Perfil {
    public id: number;
    public personaFisica: PersonaFisica;
    public personaJuridica: PersonaJuridica;
    public usuarioSistema: UsuarioSistema
    public tipoServicio: number;
    public observaciones: string;
    public codigoTipoServicio: string;
    //de aqui
    public empresa: Empresa;
    //public solicitud: Solicitud[];
    public correo: string;
    public telefono: string;
    public provincia: string;
    public canton: string;
    public distrito: string;
    public direccion: string;
    public beneficios: [Beneficio];
    // aqui
    public fechaCreacion: Date;
}
