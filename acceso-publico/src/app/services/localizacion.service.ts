import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';
import { Localizacion } from '../models/localizacion';
import { Result } from '../models/result';

@Injectable({
  providedIn: 'root'
})
export class LocalizacionService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }


  public guardarListado(listado: Localizacion[]): Promise<Localizacion[]> {
    let url = `${environment.servicioLocalizacion}/guardarLista`;
    return this.http.post<Localizacion[]>(url, listado, this.oauth.headers()).toPromise();
  }

  public eliminarListado(listado: Localizacion[]): Promise<Result> {
    let url = `${environment.servicioLocalizacion}/eliminarLista`;
    return this.http.post<Result>(url, listado, this.oauth.headers()).toPromise();
  }

  public eliminarLocalizacion(id: number): Promise<any> {
    let url = `${environment.servicioLocalizacion}/eliminar?id=${id}`;
    return this.http.post<Result>(url, id, this.oauth.headers()).toPromise();
  }

  public obtenerLocalizaciones(idPerfil: number): Promise<Localizacion[]> {
    let url = `${environment.servicioLocalizacion}/obtenerLista?idPerfil=${idPerfil}`;
    return this.http.get<Localizacion[]>(url, this.oauth.headers()).toPromise();
  }
}
