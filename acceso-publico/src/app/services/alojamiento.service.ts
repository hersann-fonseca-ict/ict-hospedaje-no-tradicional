import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';
import { Alojamiento } from '../models/alojamiento';

@Injectable({
  providedIn: 'root'
})
export class AlojamientoService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerListadoClase(): Promise<Alojamiento[]> {
    let url = `${environment.servicioAlojamiento}/listadoClase`;
    return this.http.get<Alojamiento[]>(url, this.oauth.headers()).toPromise();
  }
}
