import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Solicitud } from '../models/solicitud';
import { OAuthHelperService } from './o-auth-herlper.service';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerPorId(id: number): Promise<Solicitud> {
    let url = `${environment.servicioSolicitud}/obtenerPorId?id=${id}`;
    return this.http.get<Solicitud>(url, this.oauth.headers()).toPromise();
  }

  public guardarSolicitud(solicitud: Solicitud): Promise<Solicitud> {
    let url = `${environment.servicioSolicitud}/guardar`;
    return this.http.post<Solicitud>(url, solicitud, this.oauth.headers()).toPromise();
  }

  public listarDocumentos(ruta: string): Promise<any> {
    let url = `${environment.servicioArchivo}/listar?dir=${ruta}`;
    return this.http.get<any>(url, this.oauth.headers()).toPromise();
  }

  public subirDocumentos(documento: File, ruta: string) {
    let url = `${environment.servicioArchivo}/subir?ruta=/${ruta}`;
    let formData: FormData = new FormData();
    formData.append('archivo', documento, documento.name);
    return this.http.post<any>(url, formData, this.oauth.headers()).toPromise();
  }
}
