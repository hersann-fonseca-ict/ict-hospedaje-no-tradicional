import { Injectable, ViewChild } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { environment } from "environments/environment";
import { OAuthHelperService } from "../OAuthHerlper/oauth-helper.service";
import { UsuarioSistema } from "./usuarios";
import { DataTableDirective } from "angular-datatables";
import { Beneficio } from "../Beneficios/beneficios";

@Injectable({
  providedIn: "root",
})
export class UsuariosService {
  private clientId = environment.clientId;
  private clientSecret = environment.clientSecret;

  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;
  dtOptions: any = {};
  beneficios: Beneficio[];
  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public setBeneficios(beneficios: Beneficio[]) {
    this.beneficios = beneficios;
  }

  public getBeneficios() {
    return this.beneficios;
  }

  private url(dir: string): string {
    return this.oauth.url(dir, environment.servicioUsuarios);
  }

  public obtenerUsuarios(): Promise<Array<UsuarioSistema>> {
    return new Promise<Array<UsuarioSistema>>((exito, fallo) => {
      this.http
        .get<Array<UsuarioSistema>>(this.url("obtener"), this.oauth.headers())
        .toPromise()
        .then(exito)
        .catch((err) => {
          this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
  public obtenerPorNombre(nombre: string): Promise<UsuarioSistema> {
    return new Promise<UsuarioSistema>((exito, fallo) => {
      this.http
        .get<UsuarioSistema>(this.url(`obtenerPorNombreUsuario?nombreUsuario=${nombre}`), this.oauth.headers())
        .toPromise()
        .then(exito)
        .catch(fallo);
    });
  }

  public usuarioNuevoToken(username: string, id: string, name: string): Promise<UsuarioSistema> {
    return new Promise<UsuarioSistema>((exito, fallo) => {
      let credencialesBase64 = btoa(this.clientId + ":" + this.clientSecret);
      this.http
        .get<UsuarioSistema>(this.url(`usuarioNuevoToken?username=${username}&id=${id}&name=${name}`), this.oauth.headers("application/x-www-form-urlencoded", credencialesBase64, 'Basic'))
        .toPromise()
        .then(exito)
        .catch(fallo);
    });
  }

  public ativarInactivarUsuarios(username: string, activo: string): Promise<UsuarioSistema> {
    return new Promise<UsuarioSistema>((exito, fallo) => {
      const params = new HttpParams()
        .set("nombreUsuario", username)
        .set("activo", activo);
      this.http
        .post<UsuarioSistema>(this.url("activarInactivar"), params, this.oauth.headers("application/x-www-form-urlencoded"))
        .subscribe(data => {
          this.obtenerUsuarios();
          exito(data);
        }, fallo);
    });
  }

  guardarUsuario(usuarioNuevo: UsuarioSistema): Promise<UsuarioSistema> {
    return new Promise<UsuarioSistema>((exito, fallo) => {
      this.http
        .post<UsuarioSistema>(this.url("guardar"), usuarioNuevo, this.oauth.headers())
        .subscribe(exito, fallo);
    });
  }

  actualizarUsuario(usuario: UsuarioSistema): Promise<UsuarioSistema> {
    return new Promise<UsuarioSistema>((exito, fallo) => {
      this.http
        .post<UsuarioSistema>(this.url("guardar"), usuario, this.oauth.headers())
        .subscribe(exito, fallo);
    });
  }
}

