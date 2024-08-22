import { Injectable } from "@angular/core";
import { OAuthHelperService } from "../OAuthHerlper/oauth-helper.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "environments/environment";
import { PerfilModel } from "./perfil-model";
import { Perfil } from "./perfil";
import { PerfilExcel } from "./perfil-excel";
import { EstablecimientoPorAnalista } from "./establecimientoPorAnalista";
import { Correo } from "./correo";
import { UsuarioSistema } from "../Usuarios/usuarios";

@Injectable({
  providedIn: "root",
})
export class PerfilesService {
  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerPerfiles(): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioPerfiles + "/prueba", { headers: headers })
        .toPromise()
        .then((perfiles: [PerfilModel]) => {
          exito(perfiles);
        })
        .catch((err) => {
          //   this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }

  public obtenerPerfilPorId(idPerfil: number): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioPerfiles + "/obtenerPorId?id=" + idPerfil, {
          headers: headers,
        })
        .toPromise()
        .then((perfil: Perfil) => {
          exito(perfil);
        })
        .catch((err) => {
          fallo(err);
        });
    });
  }

  actualizarPerfil(perfil: Perfil, descripcion: string, accion: string) {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      const body = perfil;
      this.http
        .post<Perfil>(
          environment.servicioPerfiles +
          "/realizarAccion?descripcion=" +
          descripcion +
          "&accion=" +
          accion,
          body,
          { headers }
        )
        .toPromise()
        .then((data: Perfil) => {
          exito(data);
        })
        .catch((err) => fallo(err));
    });
  }

  obtenerListadoExcel() {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get<Array<PerfilExcel>>(
          environment.servicioPerfiles + "/obtenerListadoExcel",
          { headers }
        )
        .toPromise()
        .then((data: Array<PerfilExcel>) => {
          exito(data);
        })
        .catch((err) => fallo(err));
    });
  }

  obtenerListadoExcelEmpresa() {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get<Array<PerfilExcel>>(
          environment.servicioPerfiles + "/obtenerListadoExcelEmpresa",
          { headers }
        )
        .toPromise()
        .then((data: Array<PerfilExcel>) => {
          exito(data);
        })
        .catch((err) => fallo(err));
    });
  }

  asignarEstablecimentoAAnalista(idUsuario: number, idEmpresa: number, idLocalizacion: number, idJefe: number) {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get<EstablecimientoPorAnalista>(
          environment.servicioPerfiles +
          "/asignarEstablecmientoAnalista?idUsuario=" +
          idUsuario +
          "&idLocalizacion=" +
          idLocalizacion +
          "&idEmpresa=" +
          idEmpresa +
          "&idJefe=" +
          idJefe,
          { headers }
        )
        .toPromise()
        .then((data: EstablecimientoPorAnalista) => {
          exito(data);
        })
        .catch((err) => fallo(err));
    });
  }

  quitarAsignacionEstablecimentoAAnalista(idUsuario: number, idEmpresa: number, idLocalizacion: number, idJefe: number) {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .delete<EstablecimientoPorAnalista>(
          environment.servicioPerfiles +
          "/quitarAsignacionEstablecimientoAnalista?idUsuario=" +
          idUsuario +
          "&idLocalizacion=" +
          idLocalizacion +
          "&idEmpresa=" +
          idEmpresa +
          "&idJefe=" +
          idJefe,
          { headers }
        )
        .toPromise()
        .then((data: EstablecimientoPorAnalista) => {
          exito(data);
        })
        .catch((err) => fallo(err));
    });
  }

  enviarCorreoUsuario(idUsuario: number | undefined, asunto: string, cuerpo: string, destino: string | undefined = undefined): Promise<Correo> {
    let headers = new HttpHeaders({
      "Content-type": "application/json",
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
    let header = { headers };
    let body = new Correo("Solicitud de envío de correo", asunto, cuerpo, destino);
    let url = `${environment.servicioPerfiles}/enviarCorreoUsuario?idUsuario=${idUsuario ? idUsuario : ''}`;
    return new Promise<Correo>((exito, fallo) => {
      this.http
        .post<Correo>(url, body, header)
        .toPromise()
        .then(exito)
        .catch(fallo);
    });
  }

  enviarCorreoUsuarioPerfil(emailDestinatario: string, asunto: string, cuerpo: string): Promise<Correo> {
    let headers = new HttpHeaders({
      "Content-type": "application/json",
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
    let header = { headers };
    let body = new Correo("Solicitud de envío de correo", asunto, cuerpo);
    let url = `${environment.servicioPerfiles}/enviarCorreoUsuarioPerfil?emailDestinatario=${emailDestinatario}`;
    return new Promise<Correo>((exito, fallo) => {
      this.http
        .post<Correo>(url, body, header)
        .toPromise()
        .then(exito)
        .catch(fallo);
    });
  }
}
