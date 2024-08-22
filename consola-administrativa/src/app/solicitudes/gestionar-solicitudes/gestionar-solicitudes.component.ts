import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Beneficio } from 'app/services/Beneficios/beneficios';
import { BeneficiosService } from 'app/services/Beneficios/beneficios.service';
import { Solicitud } from 'app/services/Solicitudes/solicitud';
import { SolicitudesService } from 'app/services/Solicitudes/solicitudes.service';
import { TipoServicio } from 'app/services/TipoServicio/tipo-servicio';
import { TipoServicioService } from 'app/services/TipoServicio/tipo-servicio.service';

@Component({
  selector: 'app-gestionar-solicitudes',
  templateUrl: './gestionar-solicitudes.component.html',
  styleUrls: ['./gestionar-solicitudes.component.css']
})
export class GestionarSolicitudesComponent implements OnInit {
  detallesSolicitud: Solicitud;
  estadoSeleccionado='';
  nombreTipoServivio: string ='';
  listaArchivos: string [];

  beneficiosSelecionados: number[];
  listaBeneficios: Beneficio[];
  loading: boolean;
  verDetales: boolean;
  justificacion: string='';
  datos: Solicitud[] = [];

  beneficiosAsignados: Beneficio[];
  idSolicitud: number;
  identificacion: number;
  constructor(private tiposervicioService: TipoServicioService,private solicitudesService: SolicitudesService,private beneficioService: BeneficiosService,private router: Router,private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.beneficioService.obtenerBeneficios().then(data => {
      this.listaBeneficios = data;
      this.route.queryParams
      .subscribe(params => {
        this.idSolicitud = params['id'];
        this.redirectToDetails(this.idSolicitud);
      });
    });
  }
  clickedOption(){
    if(this.estadoSeleccionado == "Rechazado"){
      this.beneficiosSelecionados = [];
    }
  }
  regresarTable(){
   
    this.router.navigateByUrl('/solicitudes');
  }
  gestionarSolicitud(){
  if(this.estadoSeleccionado == "Aprobado"){
    this.detallesSolicitud.estadoSolicitud = 'APROBADA';
    this.beneficiosAsignados = this.listaBeneficios.filter(item => this.beneficiosSelecionados.indexOf(item.id) != -1);
    this.detallesSolicitud.beneficios = this.beneficiosAsignados;
    this.solicitudesService.guardarSolicitud(this.detallesSolicitud).then( (respuesta: Solicitud) => {
      
    alert( 'Se guardó la solicitud con éxito');
    this.router.navigateByUrl('/solicitudes');
    }  )
    .catch( (err) => alert('Ha ocurrido un error contacte con el administrador del sitio') );
  }else{
    this.detallesSolicitud.estadoSolicitud = 'RECHAZADA';
    this.detallesSolicitud.justificacion = this.justificacion;
    this.solicitudesService.guardarSolicitud(this.detallesSolicitud).then( (respuesta: Solicitud) => {
    alert( 'Se guardó la solicitud con éxito');

    this.router.navigateByUrl('/solicitudes');
    }  )
    .catch( (err) => alert('Ha ocurrido un error contacte con el administrador del sitio') );
  }
  }
  public redirectToDetails = (id: number) => {
    this.solicitudesService.obtenerPorId(id).then( (respuesta: Solicitud) => {
     this.detallesSolicitud = respuesta;
     this.verDetales= true;
     this.idSolicitud = this.detallesSolicitud.id;
     this.identificacion =  this.detallesSolicitud.identificacionRepresentante;
     this.obtenerlistaArchivos(this.detallesSolicitud.id, this.detallesSolicitud.identificacionRepresentante);
     this.obtenerNombreTipoServicio(this.detallesSolicitud.tipoServicio);
     this.beneficiosAsignados = this.detallesSolicitud.beneficios.filter(item => this.listaBeneficios.indexOf(item) == -1);
     this.beneficiosSelecionados = this.detallesSolicitud.estadoSolicitud =="APROBADA" ? this.beneficiosAsignados.map(({ id }) => id): this.beneficiosSelecionados = [];
     this.justificacion =  this.detallesSolicitud.estadoSolicitud =="RECHAZADA" ?  this.detallesSolicitud.justificacion : '';
     this.estadoSeleccionado = this.detallesSolicitud.estadoSolicitud =="APROBADA" ? 'Aprobado' : 'Rechazado';
   }).catch( (err) => alert('Ha ocurrido un error contacte con el administrador del sitio') );
 }
 obtenerNombreTipoServicio(id: number){
  this.tiposervicioService.obtenerPorId(id).then( (respuesta: TipoServicio) => {
      this.nombreTipoServivio=  respuesta.nombre;;
  });
}
obtenerlistaArchivos(id, identificacion){
  this.solicitudesService.listarDocumantos('archivos/'+identificacion+'/'+id).then( (respuesta) => {
      this.listaArchivos =  respuesta; 
    }).catch( (err) => this.listaArchivos = null );
}
descargarArchivo(nombre){
  this.solicitudesService.downloadFile('/'+this.identificacion+'/'+this.idSolicitud+'/'+nombre, nombre);
}
}
