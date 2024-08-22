import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { CommonService } from 'app/services/common/common.service';
import { RolesSistema } from 'app/services/Roles/roles';
import { RolesService } from 'app/services/Roles/roles.service';
import { LenguajeTabla } from 'app/table-list/lenguajeTabla';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RolesComponent implements OnInit {

  public listaRoles: [RolesSistema];
  constructor(private rolesService: RolesService,private common :  CommonService) { }
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  crearRol: boolean;
  hide: boolean;
  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;
  dtInstance: Promise<DataTables.Api>;
ngOnInit(){
  //this.rolesService.obtenerRoles().then( (listaRoles) => this.listaRoles = listaRoles ).catch( (err) => alert(err) );
    this.obtenerRoles();
    this.crearRol = false;
    this.hide = true;
}
obtenerRoles(){
  this.common._setLoading(true); 
  this.dtOptions = {
    pagingType: 'full_numbers',
    pageLength: 5,
    lengthMenu : [5, 25, 50],
    processing: true,
    serverSide: false,
    language: LenguajeTabla.spanish_datatables
  };
  this.rolesService.obtenerRoles().then(data => {
    this.listaRoles = data;
    this.dtTrigger.next();
  }).catch((err) => {
    this.common._setLoading(false); 
    alert('Ocurrió un error con los servicios. Contacte con el administrador')
  })
}
ngOnDestroy(): void {
  this.dtTrigger.unsubscribe();
}
crearNuevoRol(){
  this.crearRol= true;
}
cancelarCrearRol(){
  this.crearRol= false;
  this.obtenerRoles();
}
}
