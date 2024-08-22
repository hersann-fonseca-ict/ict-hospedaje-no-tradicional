import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';
import { Provincia } from '../models/provincia';
import { Canton } from '../models/canton';
import { Distrito } from '../models/distrito';

@Injectable({
  providedIn: 'root'
})
export class DireccionService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerProvincias(): Promise<Provincia[]> {
    let url = `${environment.servicioDireccion}/obtener`;
    return this.http.get<Provincia[]>(url, this.oauth.headers()).toPromise();
  }

  public obtenerCantones(id: number): Promise<Canton[]> {
    let url = `${environment.servicioDireccion}/obtenerCantonPorId?id=${id}`;
    return this.http.get<Canton[]>(url, this.oauth.headers()).toPromise();
  }

  public obtenerDistritos(id: number): Promise<Distrito[]> {
    let url = `${environment.servicioDireccion}/obtenerDistritoPorId?id=${id}`;
    return this.http.get<Distrito[]>(url, this.oauth.headers()).toPromise();
  }
}