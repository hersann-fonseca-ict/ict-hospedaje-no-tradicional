import { Injectable } from '@angular/core';
import { OAuthHelperService } from '../OAuthHerlper/oauth-helper.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'environments/environment';
import { TipoServicio } from './tipo-servicio';

@Injectable({
  providedIn: 'root'
})
export class TipoServicioService {
  
  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }
  public obtenerTipoServicio(): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioTipoServicio + "/obtener", { headers: headers })
        .toPromise()
        .then((tipoServicios: [TipoServicio]) => {
          exito(tipoServicios);
        })
        .catch((err) => {
     //     this.oauth.eliminarToken();
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
        .get(environment.servicioTipoServicio + "/obtenerPorId?id="+id, { headers: headers })
        .toPromise()
        .then((tipoServicio: TipoServicio) => {
          exito(tipoServicio);
        })
        .catch((err) => {
    //      this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
  guardarTipoServicio(tipoServicio: TipoServicio) {
  
    return new Promise((exito, fallo) => {
    let headers = new HttpHeaders({
      "Content-type": "application/json",
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
    const body = tipoServicio;
    this.http
    .post<TipoServicio>(environment.servicioTipoServicio +'/guardar', body, { headers })
    .subscribe(
      (data: TipoServicio) => {
      ;
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
