import { Injectable } from '@angular/core';
import { OAuthHelperService } from '../OAuthHerlper/oauth-helper.service';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { environment } from 'environments/environment';
import { Solicitud } from './solicitud';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SolicitudesService {


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
        .then((solicitudes: [Solicitud]) => {
          exito(solicitudes);
        })
        .catch((err) => {
     //     this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
  public obtenerPorId(id): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
      .get(environment.servicioSolicitud + "/obtenerPorId?id="+id, { headers: headers })
      .subscribe(
        (solicitud: Solicitud) => {
          exito(solicitud);
        },
        (err) => fallo(err)
      );
  });
  }
  public obtenerDocumento(ruta): Promise<any> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
      .get(environment.servicioArchivo + "/bajar?ruta="+ruta, { headers: headers })
      .subscribe(
        (respuesta) => {
          exito(respuesta);
        },
        (err) => fallo(err)
      );
  });
  }
  downloadFile(route: string, filename: string = null): void{

    const baseUrl = environment.servicioArchivo + "/bajar?ruta=";
    const token = this.oauth.obtenerTokenAlmacenado();
    const headers = new HttpHeaders().set('authorization','Bearer '+token);
    this.http.get(baseUrl + route,{headers, responseType: 'blob' as 'json'}).subscribe(
        (response: any) =>{
            let dataType = response.type;
            let binaryData = [];
            binaryData.push(response);
            let downloadLink = document.createElement('a');
            downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
            if (filename)
                downloadLink.setAttribute('download', filename);
            document.body.appendChild(downloadLink);
            downloadLink.click();
        }
    )
}
  public listarDocumantos(ruta): Promise<any> {
    return new Promise((exito, fallo) => {
      
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
      .get(environment.servicioArchivo + "/listar?dir="+ruta, { headers: headers })
      .subscribe(
        (respuesta) => {
          exito(respuesta);
        },
        (err) => fallo(err)
      );
  });
  }
  listTodos(pagina, tamano) {
    const endpoint = environment.servicioSolicitud + "/obtenerPaginados";
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
  guardarSolicitud(solicitud){
    return new Promise((exito, fallo) => {
    let headers = new HttpHeaders({
      "Content-type": "application/json",
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
    const body = solicitud;
    this.http.post<Solicitud>(environment.servicioSolicitud +'/guardar', body, { headers }).subscribe(
      (data: Solicitud) => {
      
        exito(data);
      },
      (err) => fallo(err)
    );
  });
}
}
