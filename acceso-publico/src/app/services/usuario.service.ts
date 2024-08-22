import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';
import { UsuarioSistema } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public obtenerPorNombreUsuario(username: string): Promise<UsuarioSistema> {
    let url = `${environment.servicioUsuarios}/obtenerPorNombreUsuario?nombreUsuario=${username}`;
    return this.http.get<UsuarioSistema>(url, this.oauth.headers()).toPromise();
  }

  obtenerUsuario() {
    return this.oauth.obtenerUsuarioAlmacenado();
  }
  
  sesionValida() {
    return this.oauth.sesionValida();
  }
}
