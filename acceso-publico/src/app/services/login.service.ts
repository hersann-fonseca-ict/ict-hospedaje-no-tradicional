import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';
import { RespuestaToken } from '../models/respuesta-token';
import { UsuarioSistema } from "../models/usuario";
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private common: CommonService, private oauth: OAuthHelperService, private http: HttpClient, private router: Router) { }

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  registrarUsuario(usuarioNuevo: UsuarioSistema): Promise<UsuarioSistema> {
    let url = `${environment.servicioUsuarios}/nuevoCliente`;
    return this.http.post<UsuarioSistema>(url, usuarioNuevo).toPromise();
  }

  solicitarCodigo(nombreUsuario): Promise<String> {
    let url = `${environment.servicioUsuarios}/solicitarCodigoVerificacion?username=${nombreUsuario}`;
    return this.http.get<String>(url).toPromise();
  }

  cambiarContrasena(nombreUsuario, codigo, contrasena): Promise<UsuarioSistema> {
    let url = `${environment.servicioUsuarios}/cambiarClave?username=${nombreUsuario}` +
      `&verificationCode=${codigo}&newPassword=${contrasena}`;
    return this.http.get<UsuarioSistema>(url).toPromise();
  }

  public obtenerUsuarioCambio(nombreUsuario: string): Promise<UsuarioSistema> {
    let url = `${environment.servicioUsuarios}/obtenerPorNombreUsuario?nombreUsuario=${nombreUsuario}`;
    return new Promise((exito, fallo) => {
      this.http.get<UsuarioSistema>(url, this.oauth.headers())
        .toPromise()
        .then(exito)
        .catch(fallo)
        .then(this.eliminarToken);
    });
  }

  iniciarSession(nombreUsuario: string, contrasena: string) {
    this.common._setLoading(true);
    this.eliminarToken();
    this.oauth.autenticar(nombreUsuario, contrasena)
      .then((respuesta: RespuestaToken) => {
        this.oauth.guardarToken(respuesta.access_token, nombreUsuario, respuesta.correo);
        this.loggedIn.next(true);
        this.router.navigate(['/registro']);
      }).catch((err) => {
        this.common._setLoading(false);
        alert('Usuario ó contraseña invalidos');
      })
  }

  cerrarSesion() {
    this.eliminarToken();
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  obtenerUsuario(): string | null {
    return this.oauth.obtenerUsuarioAlmacenado();
  }

  obtenerToken(): string | null {
    return this.oauth.obtenerTokenAlmacenado();
  }

  eliminarToken() {
    this.oauth.eliminarToken();
  }

  sessionValida(): boolean {
    return this.oauth.sesionValida();
  }
}
