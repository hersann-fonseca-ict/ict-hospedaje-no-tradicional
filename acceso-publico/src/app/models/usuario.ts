import { RolesSistema } from "./roles";

export class UsuarioSistema {
    public id: number;
    public nombreUsuario: string;
    public clave: string;
    public activo: boolean;
    public correo: string;
    public ultimoCodigoVerificacion: string;
    public fechaCreacion: Date;
    public roles: RolesSistema[];
}