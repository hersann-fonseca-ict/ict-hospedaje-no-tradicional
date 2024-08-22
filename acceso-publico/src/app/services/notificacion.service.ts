import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { OAuthHelperService } from './o-auth-herlper.service';

@Injectable({
  providedIn: 'root'
})
export class NotificacionService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService) { }

  public enviarNotificacion(numeroFormulario: any): Promise<any> {
    let url = `${environment.servicioNotificaciones}/enviarCorreo`
      + `?destino${this.oauth.obtenerCorreoAlmacenado()}`
      + "&asunto=ICT - Beneficios&cuerpo=Su solicitud de beneficios se registró correctamente con el número de solicitud "
      + `${numeroFormulario} el estado de la solicitud podrá ser consultado desde el portal web`;
    return this.http.get(url, this.oauth.headers()).toPromise();
  }
}