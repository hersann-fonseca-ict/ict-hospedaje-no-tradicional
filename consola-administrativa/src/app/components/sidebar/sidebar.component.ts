import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { OAuthHelperService } from 'app/services/OAuthHerlper/oauth-helper.service';


import { LogoutComponent } from '../logout/logout.component';
import { OpcionMenuModel } from '../../services/Modelos/opciones-Menu';
import { element } from 'protractor';
import { CommonService } from 'app/services/common/common.service';

declare const $: any;



declare interface RouteInfo {
  id: number;
  path: string;
  title: string;
  icon: string;
  class: string;
  father: boolean;
  childs: RouteInfo[];
  optionActive: boolean;


}
export const ROUTES: RouteInfo[] = [
  //{ path: '/beneficios', title: 'Configuración de Beneficios',  icon: 'dashboard', class: '' },

  {
    id: 9, path: '', title: 'Clientes', icon: 'topic', class: '', father: true, optionActive: false, childs: [
      { id: 1, path: '/perfiles', title: ' Consulta de Clientes', icon: 'topic', class: '', father: false, optionActive: false, childs: [] },
      { id: 7, path: '/asignar-analista', title: 'Asignar Inmubles por Analista', icon: 'person', class: '', father: false, optionActive: false, childs: [] },
      { id: 8, path: '/desafiliar', title: 'Pendientes de desafiliar', icon: 'person', class: '', father: false, optionActive: false, childs: [] }
    ]
  },
  {
    id: 3, path: '', title: 'Reportes', icon: 'topic', class: '', father: true, optionActive: false, childs: [

      { id: 4, path: 'reporte-hacienda', title: 'Reporte Hacienda', icon: 'topic', class: '', father: false, optionActive: false, childs: [] },
      { id: 5, path: 'reporte-graficos', title: 'Reporte Gráficos', icon: 'topic', class: '', father: false, optionActive: false, childs: [] }]
  },
  //{ path: '/roles', title: 'Roles',  icon:'content_paste', class: '' },
  //{ path: '/solicitudes', title: 'Solicitud de Beneficios',  icon:'library_books', class: '' },
  //{ path: '/tipo-servicios', title: 'Tipos de servicios',  icon:'bubble_chart', class: '' },
  { id: 6, path: '/usuarios', title: 'Usuarios de sistema', icon: 'people', class: '', father: false, optionActive: false, childs: [] }

];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})

export class SidebarComponent implements OnInit {
  menuItems: any[];
  opcionesModel: OpcionMenuModel = new OpcionMenuModel();
  menuItemsChildREPORT: any[];
  // optionActive: boolean= false; 
  optionIDSelected: number = 0;
  lista_hijas: OpcionMenuModel[];
  lista_padres: OpcionMenuModel[];
  lista_temp: OpcionMenuModel[];
  dataDecode: any;
  constructor(private _common: CommonService, public matDialog: MatDialog, private _router: Router, private oauth: OAuthHelperService) { }

  ngOnInit() {
    this._common._setLoading(true)
    this.dataDecode = this.oauth.obtenerTokenDecode();

    this.getMenuOptions();
    this.menuItems = ROUTES.filter(menuItem => menuItem);

  }
  isMobileMenu() {
    if ($(window).width() > 991) {
      return false;
    }
    return true;
  };
  abrirModal() {
    const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.height = "250px";
    dialogConfig.width = "500px";
    // https://material.angular.io/components/dialog/overview
    const modalDialog = this.matDialog.open(LogoutComponent, dialogConfig);

  }

  getMenuOptions() {
    this._common._setLoading(true);

    this.oauth.obtenerOpcionesMenu(this.dataDecode.id).then(async (datos) => {
      datos.forEach(element => {
        if (this.opcionesModel.lista_opciones.filter(x => x.id == element.opciones_menu.id).length == 0) {
          this.opcionesModel.lista_opciones.push(element.opciones_menu);
        }

      });

      this.lista_hijas = this.opcionesModel.lista_opciones.filter(x => x.fk_opcion != 0);
      this.lista_padres = this.opcionesModel.lista_opciones.filter(x => x.fk_opcion == 0);
      this.lista_hijas.forEach(element => {
        if (element.clase == null) {
          element.clase = "";
        }
      })

      this.lista_padres.forEach(element => {
        if (element.clase == null) {
          element.clase = "";
        }
        element.lista_opciones = this.lista_hijas.filter(x => x.fk_opcion == element.id);
      })
      this._common._setLoading(false);

    });
  }


  setActiveOption(id) {
    this.optionIDSelected = id;
    var opcion = this.lista_padres.filter(x => x.id == id)[0];
    this.lista_padres.forEach(element => {
      element.opcionTurno = true;
    });
    opcion.opcionTurno = true;
  }

  setDesactiveOption(id) {
    var opcion = this.lista_padres.filter(x => x.id == id)[0];
    this.lista_padres.forEach(element => {
      element.opcionTurno = false;
    });
    opcion.opcionTurno = false;

  }

  goToChildPage(path) {

    this._router.navigate([path]);
  }

}
