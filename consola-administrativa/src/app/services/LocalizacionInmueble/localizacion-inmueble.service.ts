import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { LocalizacionInmueble } from '../Modelos/localizacion-inmueble';
import { OAuthHelperService } from '../OAuthHerlper/oauth-helper.service';

@Injectable({
  providedIn: 'root'
})
export class LocalizacionInmuebleService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerInmublePorId(id: number): Promise<LocalizacionInmueble>{
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioLocalizacion + "/obtenerInmuebleById?id=" + id, { headers: headers })
        .toPromise()
        .then((inmueble: LocalizacionInmueble) => {
          exito(inmueble);
        })
        .catch((err) => { 
          fallo(err);
        });
    });
  }

  public actualizarInmueble(inmueble: LocalizacionInmueble): Promise<LocalizacionInmueble>{
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      const body = inmueble;
      this.http
        .post<LocalizacionInmueble>(environment.servicioLocalizacion + '/realizarAccion', body, { headers })
        .toPromise()
        .then((data: any) => {
          exito(data);
        }).catch((err) => fallo(err));
    });
  }

  public enviarCorreo(inmueble: LocalizacionInmueble, descripcion: string, accion: string, cuerpo: string): Promise<LocalizacionInmueble>{
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      const body = inmueble;
      this.http
        .post<LocalizacionInmueble>(environment.servicioLocalizacion + '/enviarCorreo?descripcion='+descripcion+'&accion='+accion+'&param='+cuerpo, body, { headers })
        .toPromise()
        .then((data: any) => {
          exito(data);
        }).catch((err) => fallo(err));
    });
  }
}
