import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TipoServicio } from 'app/services/TipoServicio/tipo-servicio';
import { TipoServicioService } from 'app/services/TipoServicio/tipo-servicio.service';
import { LenguajeTabla } from 'app/table-list/lenguajeTabla';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-tipo-servicios',
  templateUrl: './tipo-servicios.component.html',
  styleUrls: ['./tipo-servicios.component.css']
})
export class TipoServiciosComponent implements OnInit {
  public listaTipoServicio: [TipoServicio];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  crearTipo: boolean = false;
  nombreTipoServicio: string = '';
  necesitaCodigo: string= '';
  nuevoTipoServicio:  TipoServicio = new TipoServicio();
  roles: string[];
  constructor(private tipoServiciosServices: TipoServicioService,private router: Router) {
    this.roles = tipoServiciosServices.obtenerRoles();
   }

  ngOnInit() {
    this.obtenerTipoServicio();
  }
  obtenerTipoServicio(){
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu : [5, 10, 15],
      processing: true,
      language: LenguajeTabla.spanish_datatables
    };
    this.tipoServiciosServices.obtenerTipoServicio().then(data => {
      this.listaTipoServicio = data;
      this.dtTrigger.next();
    });
  }
  crearNuevoTipoServicio(){
    this.router.navigateByUrl('/gestionar-tipoServicio');
  }

  modificartipoServicio(tipoServicio:TipoServicio ){
    this.router.navigate(['/gestionar-tipoServicio'], { queryParams: { id: tipoServicio.id }});   

  }

 

}
