import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "environments/environment";
import { RespuestaToken } from "./respuesta-token";
import { TokenDecode } from "./token-decode";
@Injectable({
  providedIn: "root",
})
export class OAuthHelperService {
  private clientId = environment.clientId;
  private clientSecret = environment.clientSecret;
  public oauthURL = environment.oauthURL;
  public token: TokenDecode;

  constructor(private _http: HttpClient) { }

  public headers(contentType: string | undefined = 'application/json', token: string | undefined = this.obtenerTokenAlmacenado(), type: 'Bearer' | 'Basic' | undefined = 'Bearer'): Object {
    return {
      headers: new HttpHeaders({
        "Content-type": contentType,
        Authorization: `${type} ${token}`,
      })
    };
  }

  public url(dir: string = undefined, base: string = environment.oauthURL): string {
    return `${base}${dir ? '/' + dir : ''}`;
  }

  autenticar(nombreUsuario: string, pass: string): Promise<RespuestaToken> {
    return new Promise<RespuestaToken>((resolucion, fallo) => {
      let credencialesBase64 = btoa(this.clientId + ":" + this.clientSecret);
      const params = new HttpParams()
        .set("grant_type", "password")
        .set("username", nombreUsuario + "&OCA")
        .set("password", pass)
        .set("scope", "")
        .set("office", "1")
        .set("consola-administrativa", "1");
      this._http.post<RespuestaToken>(this.url(), params.toString(), this.headers("application/x-www-form-urlencoded", credencialesBase64, 'Basic'))
        .subscribe(data => {
          this.guardarToken(data.access_token);
          resolucion(data);
        },
          fallo
        );
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

  private deleteCookie(name): void {
    this.setCookie(name, "", -1);
  }

  private setCookie(
    name: string,
    value: string,
    expireDays: number,
    path: string = ""
  ): void {
    let d: Date = new Date();
    d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
    let expires: string = `expires=${d.toUTCString()}`;
    let cpath: string = path ? `; path=${path}` : "";
    document.cookie = `${name}=${value}; ${expires}${cpath}`;
  }

  public sesionValida(): boolean {
    return !!(this.obtenerTokenAlmacenado());
  }

  private tokenExpired(token: string): boolean {
    const expiry = JSON.parse(atob(token.split(".")[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }

  obtenerTokenAlmacenado(): string | null {
    let token = this.getCookie("token");
    if (token) {
      if (!this.tokenExpired(token)) return token;
      this.eliminarToken();
    }
    return null;
  }

  eliminarToken(): void {
    this.deleteCookie("token");
  }

  guardarToken(token: string): void {
    this.setCookie("token", token, 1);
  }

  obtenerTokenDecode(): TokenDecode {
    let decoded = atob(this.obtenerTokenAlmacenado().split('.')[1]);
    this.token = JSON.parse(decoded)
    return this.token;
  }

  obtenerRoles(): string[] {
    return this.obtenerTokenDecode().authorities;
  }

  public obtenerOpcionesMenu(Id: number): Promise<any[]> {
    return new Promise<any[]>((exito, fallo) => {
      this._http
        .get<any[]>(this.url(`obtenerOpcionesMenuPorIdUsuario?Id=${Id}`, environment.servicioUsuarios), this.headers())
        .toPromise()
        .then(exito)
        .catch((err) => {
          this.eliminarToken();
          fallo(err);
        });
    });
  }
}
