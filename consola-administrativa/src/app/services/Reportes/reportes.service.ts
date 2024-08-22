import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'environments/environment';
import { ReporteGraficosModel } from '../Modelos/reporte-graficos-model';
import { OAuthHelperService } from '../OAuthHerlper/oauth-helper.service';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  constructor(private http: HttpClient, private oauth: OAuthHelperService){ }

  public obtenerReporteProvincias(): Promise<ReporteGraficosModel[]> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });

      this.http
        .get(environment.servicioLocalizacion + "/obtenerGraficoPorProvincia", { headers: headers })
        .toPromise()
        .then((datos: ReporteGraficosModel[]) => {
          exito(datos);
        })
        .catch((err) => {
       //   this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }

  
  public obtenerReporteAlojamiento(): Promise<ReporteGraficosModel[]> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioAlojamiento + "/obtenerGraficoAlojamiento", { headers: headers })
        .toPromise()
        .then((datos: ReporteGraficosModel[]) => {
          exito(datos);
        })
        .catch((err) => {
        //  this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }

  
  public obtenerReportePerfiles(): Promise<ReporteGraficosModel[]> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioPerfiles + "/obtenerGraficoTipoServicio", { headers: headers })
        .toPromise()
        .then((datos: ReporteGraficosModel[]) => {
          exito(datos);
        })
        .catch((err) => {
       //   this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }

    
  public obtenerCantidadPerfiles(): Promise<ReporteGraficosModel[]> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioPerfiles + "/obtenerCantidadPerfiles", { headers: headers })
        .toPromise()
        .then((datos: ReporteGraficosModel[]) => {
          exito(datos);
        })
        .catch((err) => {
       //   this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }



  public obtenerPerfilesConInmuebles(): Promise<ReporteGraficosModel[]> {
    return new Promise((exito, fallo) => {
      let headers = new HttpHeaders({
        "Content-type": "application/json",
        Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
      });
      this.http
        .get(environment.servicioLocalizacion + "/ObtenerPerfilesConInmuebles", { headers: headers })
        .toPromise()
        .then((datos: ReporteGraficosModel[]) => {
          exito(datos);
        })
        .catch((err) => {
       //   this.oauth.eliminarToken();
          fallo(err);
        });
    });
  }
}
