import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';
import { Perfil } from '../models/perfiles';
import { DataTablesResponse } from '../models/data-tables-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerPerfil(username: string): Promise<Perfil> {
    let url = `${environment.servicioPerfil}/obtenerPorNombreUsuario?nombreUsuario=${username}`;
    return this.http.get<Perfil>(url, this.oauth.headers()).toPromise();
  }

  public guardarPerfil(nuevoPerfil: Perfil): Promise<Perfil> {
    let url = `${environment.servicioPerfil}/guardar`;
    return this.http.post<Perfil>(url, nuevoPerfil, this.oauth.headers()).toPromise();
  }

  public obtenerTablaEmpresas(params: any): Observable<DataTablesResponse> {
    let url = `${environment.servicioPerfil}/obtenerTablaEmpresasEm`;
    return this.http.post<DataTablesResponse>(url, params, this.oauth.headers());
  }
}