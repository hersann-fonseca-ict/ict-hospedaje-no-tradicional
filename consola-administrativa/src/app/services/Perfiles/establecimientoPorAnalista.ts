import { Empresa } from "../Modelos/empresa";
import { LocalizacionInmueble } from "../Modelos/localizacion-inmueble";
import { UsuarioSistema } from "../Usuarios/usuarios";

export class EstablecimientoPorAnalista{
    public id: number;
    public usuarioSistema : UsuarioSistema;
    public localizacion : LocalizacionInmueble;
    public empresa: Empresa;
    public jefe : UsuarioSistema;
}
