import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonService } from 'app/services/common/common.service';
import { OAuthHelperService } from 'app/services/OAuthHerlper/oauth-helper.service';
import { RolesSistema } from 'app/services/Roles/roles';
import { RolesService } from 'app/services/Roles/roles.service';
import { UsuarioSistema } from 'app/services/Usuarios/usuarios';
import { UsuariosService } from 'app/services/Usuarios/usuarios.service';
import { DataTablesResponse } from 'app/table-list/datatableRespose';
import { LenguajeTabla } from 'app/table-list/lenguajeTabla';
import { environment } from 'environments/environment';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})

export class UsuariosComponent implements OnInit {

  public listaUsuarios: UsuarioSistema[];
  dtTrigger: Subject<any> = new Subject();
  crearUsuario: boolean;
  hide: boolean;
  modificar: boolean = false;
  cambioContrasena: boolean = false;
  listaRoles: RolesSistema[];
  rolesSelecionados: number[];
  rolesAignados: number[];
  asignarRoles: RolesSistema[];
  usuarioNuevo: UsuarioSistema = new UsuarioSistema();
  dtOptions: DataTables.Settings = {};
  nombreUsuario: string = '';
  clave: string = '';
  claveConfirmar: string = '';
  correo: string = '';
  idUsuario: number;
  roles: string[];

  constructor(private common: CommonService, private usuariosService: UsuariosService, private rolesService: RolesService, private http: HttpClient, private oauth: OAuthHelperService, private router: Router) {
    this.roles = oauth.obtenerRoles();
    this.obtenerUsuarios();
  }

  ngOnInit() {
    this.common._setLoading(true);
    this.rolesService.obtenerRoles().then((listaRoles) => this.listaRoles = listaRoles).catch((err) => alert(err));
    this.crearUsuario = false;
    this.hide = true;
  }

  obtenerUsuarios() {
    let headers = new HttpHeaders({
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
    var cabecera: Object = new Object();
    cabecera['headers'] = headers;
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      autoWidth: false,
      responsive: true,
      lengthMenu: [5, 25, 50],
      processing: true,
      serverSide: true,
      language: LenguajeTabla.spanish_datatables,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
          .post<DataTablesResponse>(environment.servicioUsuarios + "/ObtenerTable",
            dataTablesParameters, cabecera
          ).subscribe(resp => {
            this.listaUsuarios = resp.data;
            this.common._setLoading(false);
            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: [],
            });
          }, error => {
            this.common._setLoading(false);
            alert('Ocurrio un error con los servicios. Contacte con el administrador')
          })
      },
      columns: [{ data: 'id' }, { data: 'nombreUsuario' }, { data: "correo" }, { data: 'activo' }]
    };
  }

  activarInactivar(usuario: UsuarioSistema, activo) {
    usuario.activo = activo;
    this.usuariosService.actualizarUsuario(usuario).then((respuesta: UsuarioSistema) => {
      this.obtenerUsuarios();
    }).catch((err) => {
      this.common._setLoading(false);
      alert('Ocurrio un error con los servicios. Contacte con el administrador')
    })
  }

  cambiarContrasena(usuario: UsuarioSistema) {
    this.cambioContrasena = true;
    this.crearUsuario = true;
    this.modificar = false;
  }

  crearNuevoUsuario() {
    this.router.navigateByUrl('/gestionar-usuarios');
  }

  ngOnDestroy(): void { }

  cancelarCrearUsuario() { }

  modificarUsuario(usuario: UsuarioSistema) {
    this.router.navigate(['/gestionar-usuarios'], { queryParams: { nombre: usuario.nombreUsuario } });
  }
}