import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams  } from "@angular/common/http";
import { environment } from "environments/environment";
import { OAuthHelperService } from "../OAuthHerlper/oauth-helper.service";
import { RolesSistema } from "./roles";

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) {}
  public obtenerRoles(): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioRoles + "/obtener", { headers: headers })
        .toPromise()
        .then((roles: [RolesSistema]) => {
          exito(roles);
        })
        .catch((err) => {
        //  this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
}
