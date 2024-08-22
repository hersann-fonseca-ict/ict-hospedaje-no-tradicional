import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Empresa } from '../Modelos/empresa';
import { OAuthHelperService } from '../OAuthHerlper/oauth-helper.service';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerEmpresaPorId(id: number): Promise<Empresa>{
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioPerfiles + "/obtenerEmpresaPorId?id=" + id, { headers: headers })
        .toPromise()
        .then((inmueble: Empresa) => {
          exito(inmueble);
        })
        .catch((err) => { 
          fallo(err);
        });
    });
  }

  public actualizarEmpresa(inmueble: Empresa): Promise<Empresa>{
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      const body = inmueble;
      this.http
        .post<Empresa>(environment.servicioPerfiles + '/realizarAccionEmpresa', body, { headers })
        .toPromise()
        .then((data: any) => {
          exito(data);
        }).catch((err) => fallo(err));
    });
  }

  public enviarCorreoEmpresa(empresa: Empresa, descripcion: string, accion: string, cuerpo: string): Promise<Empresa>{
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      const body = empresa;
      this.http
        .post<Empresa>(environment.servicioPerfiles + '/enviarCorreoEmpresa?descripcion='+descripcion+'&accion='+accion+'&param='+cuerpo, body, { headers })
        .toPromise()
        .then((data: any) => {
          exito(data);
        }).catch((err) => fallo(err));
    });
  }
}
