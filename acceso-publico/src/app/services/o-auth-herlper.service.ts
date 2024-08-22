import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { RespuestaToken } from "../models/respuesta-token";
import { TokenDecode } from "../models/token-decode";

@Injectable({
  providedIn: "root",
})
export class OAuthHelperService {
  private clientId = environment.clientId;
  private clientSecret = environment.clientSecret;
  token: TokenDecode;

  public oauthURL = environment.oauthURL;

  constructor(private _http: HttpClient) { }

  private _headers(): { headers: HttpHeaders } {
    let credenciales = this.clientId + ":" + this.clientSecret;
    return {
      headers: new HttpHeaders({
        "Content-type": "application/x-www-form-urlencoded",
        Authorization: `Basic ${btoa(credenciales)}`,
      })
    };
  }

  private _params(username: string, passowrd: string): string {
    return new HttpParams()
      .set("grant_type", "password")
      .set("username", username)
      .set("password", passowrd)
      .set("scope", "")
      .toString();
  }

  autenticar(nombreUsuario: string, clave: string): Promise<RespuestaToken> {
    const params = this._params(nombreUsuario, clave);
    return new Promise<RespuestaToken>((resolucion, fallo) => {
      this._http.post<RespuestaToken>(this.oauthURL, params, this._headers())
        .subscribe(data => {
          this.guardarToken(data.access_token, nombreUsuario, data.correo);
          resolucion(data);
        }, fallo);
    });
  }

  private getCookie(name: string): string {
    let ca: Array<string> = document.cookie.split(";");
    let caLen: number = ca.length;
    let cookieName = `${name}=`;
    let c: string;

    for (let i: number = 0; i < caLen; i += 1) {
      c = ca[i].replace(/^\s+/g, "");
      if (c.indexOf(cookieName) == 0) {
        return c.substring(cookieName.length, c.length);
      }
    }
    return "";
  }

  private deleteCookie(name) {
    this.setCookie(name, "", -1);
  }

  private setCookie(
    name: string,
    value: string,
    expireDays: number,
    path: string = ""
  ) {
    let d: Date = new Date();
    d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
    let expires: string = `expires=${d.toUTCString()}`;
    let cpath: string = path ? `; path=${path}` : "";
    document.cookie = `${name}=${value}; ${expires}${cpath}`;
  }

  public sesionValida(): boolean {
    return !(!this.obtenerTokenAlmacenado());
  }

  private tokenExpired(token: string) {
    const expiry = JSON.parse(atob(token.split(".")[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }

  obtenerTokenAlmacenado(): string | null {
    let token = this.getCookie("token");
    if (token) {
      if (this.tokenExpired(token)) {
        this.eliminarToken();
        return null;
      }
      return token;
    }
    return null;
  }

  obtenerUsuarioAlmacenado(): string | null {
    let usuario = this.getCookie("nombreUsuario");
    let token = this.getCookie("token");
    if (token) {
      if (this.tokenExpired(token)) {
        this.eliminarToken();
        return null;
      }
      return usuario;
    }
    return null;
  }

  obtenerCorreoAlmacenado(): string | null {
    let usuario = this.getCookie("correo");
    let token = this.getCookie("token");
    if (token) {
      if (this.tokenExpired(token)) {
        this.eliminarToken();
        return null;
      }
      return usuario;
    }
    return null;
  }

  eliminarToken() {
    this.deleteCookie("token");
    this.deleteCookie("nombreUsuario");
    this.deleteCookie("correo");
  }

  guardarToken(token: string, usuario: string, correo: string) {
    this.setCookie("token", token, 1);
    this.setCookie("nombreUsuario", usuario, 1);
    this.setCookie("correo", correo, 1);
  }

  headers() {
    return {
      headers: new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.obtenerTokenAlmacenado()}`,
      })
    };
  }
}