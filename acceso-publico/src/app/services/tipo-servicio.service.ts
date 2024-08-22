import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';
import { TipoServicio } from '../models/tipo-servicio';

@Injectable({
  providedIn: 'root'
})
export class TipoServicioService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerTipoServicio(): Promise<TipoServicio[]> {
    let url = `${environment.servicioTipoServicio}/obtener`;
    return this.http.get<TipoServicio[]>(url, this.oauth.headers()).toPromise();
  }
}