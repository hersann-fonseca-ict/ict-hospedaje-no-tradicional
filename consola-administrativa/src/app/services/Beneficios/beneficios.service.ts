import { Injectable } from '@angular/core';
import { OAuthHelperService } from '../OAuthHerlper/oauth-helper.service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'environments/environment';
import { Beneficio } from './beneficios';
import { Solicitud } from '../Solicitudes/solicitud';

@Injectable({
  providedIn: 'root'
})
export class BeneficiosService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerSolicitudes(): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioSolicitud + "/obtener", { headers: headers })
        .toPromise()
        .then((solicitud: [Solicitud]) => {
          exito(solicitud);
        })
        .catch((err) => {
       //   this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
  public obtenerBeneficios(): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioBeneficio + "/obtener", { headers: headers })
        .toPromise()
        .then((beneficios: [Beneficio]) => {
          exito(beneficios);
        })
        .catch((err) => {
      //    this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
  public obtenerPorId(id:number): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioBeneficio + "/obtenerPorId?id="+id, { headers: headers })
        .toPromise()
        .then((beneficio: Beneficio) => {
          exito(beneficio);
        })
        .catch((err) => {
      //    this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
  listTodos(pagina, tamano) {
    const endpoint = environment.servicioBeneficio + "/obtenerPaginados2";
    const params = new HttpParams()
        .set("pagina", pagina)
        .set("tamano", tamano)
        .set("columna", "id");
        let headers = new HttpHeaders({
          "Content-type": "application/json",
          Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
        });
    return this.http.get(endpoint,{headers: headers, params: params}); 
  }
  guardarBeneficio(nuevoBeneficio: Beneficio) {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      const body = nuevoBeneficio;
      this.http
      .post<Beneficio>(environment.servicioBeneficio +'/guardar', body, { headers })
      .subscribe(
        (data: Beneficio) => {
          exito(data);
        },
        (err) => fallo(err)
      );
    });
  }
  obtenerRoles(){
    return this.oauth.obtenerRoles();
  }
}
