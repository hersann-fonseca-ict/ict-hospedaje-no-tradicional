import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TipoServicio } from 'app/services/TipoServicio/tipo-servicio';
import { TipoServicioService } from 'app/services/TipoServicio/tipo-servicio.service';

@Component({
  selector: 'app-gestionar-tipo-servicios',
  templateUrl: './gestionar-tipo-servicios.component.html',
  styleUrls: ['./gestionar-tipo-servicios.component.css']
})
export class GestionarTipoServiciosComponent implements OnInit {
  necesitaCodigo: string= '';
  tituloText: string= 'Nuevo ';

  nuevoTipoServicio:  TipoServicio = new TipoServicio();
  nombreTipoServicio: string = '';
  idTipo: number;
  constructor(private tipoServiciosServices: TipoServicioService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams
    .subscribe(params => {
      this.idTipo = params['id'];
    });
    if(this.idTipo !=null){
    this.tipoServiciosServices.obtenerPorId(this.idTipo).then( (respuesta: TipoServicio) => {
       this.nuevoTipoServicio= respuesta;
       if(this.nuevoTipoServicio.id != null){
        this.modificartipoServicio(this.nuevoTipoServicio);
      }
      
    }).catch( (err) => alert(err.status == '500' ? 'Ha ocurrido un error, contacte con el administrador de sitio': 'Error inesperado'));
  }
  }
  guardarTipoServicio(){
    this.nuevoTipoServicio.nombre = this.nombreTipoServicio;
    this.nuevoTipoServicio.necesitaCodigo = this.necesitaCodigo =="Sí" ? true : false;
    this.nuevoTipoServicio.fechaCreacion = new Date();
    this.tipoServiciosServices.guardarTipoServicio(this.nuevoTipoServicio).then( (respuesta: TipoServicio) => {
    this.nuevoTipoServicio = respuesta;
    
    alert( 'Se guardó el tipo de servicio con éxito');
    this.router.navigateByUrl('/tipo-servicios');

    }  )
    .catch( (err) => alert(err.status == '409' ? 'Ya existe un tipo de servicio con ese nombre': 'Ha ocurrido un error contacte con el administrador del sitio'));
  }
  cancelarCrearTipoServicio(){
    this.router.navigateByUrl('/tipo-servicios');

  }
  modificartipoServicio(tipoServicio:TipoServicio ){
    this.nuevoTipoServicio = tipoServicio;
    this.nombreTipoServicio = tipoServicio.nombre;
    this.necesitaCodigo= tipoServicio.necesitaCodigo ? 'Sí' : 'No';
    this.tituloText= 'Modificar ';
 
  }
}
