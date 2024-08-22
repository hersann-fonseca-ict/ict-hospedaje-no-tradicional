import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonService } from 'app/services/common/common.service';
import { RolesSistema } from 'app/services/Roles/roles';
import { RolesService } from 'app/services/Roles/roles.service';
import { UsuarioSistema } from 'app/services/Usuarios/usuarios';
import { UsuariosService } from 'app/services/Usuarios/usuarios.service';

@Component({
  selector: 'app-gestionar-usuarios',
  templateUrl: './gestionar-usuarios.component.html',
  styleUrls: ['./gestionar-usuarios.component.css']
})
export class GestionarUsuariosComponent implements OnInit {

  public listaUsuarios: UsuarioSistema[];
  crearUsuario: boolean;
  hide: boolean;
  valido: boolean = false;
  modificar: boolean = false;
  tituloTexto: string = 'Nuevo ';
  cambioContrasena: boolean = false;
  listaRoles: RolesSistema[];
  rolesSelecionados: number[];
  rolesAignados: number[];
  asignarRoles: RolesSistema[];
  usuarioNuevo: UsuarioSistema = new UsuarioSistema();
  nombreUsuario: string = '';
  clave: string = '';
  claveConfirmar: string = '';
  correo: string = '';
  idUsuario: number;

  constructor(private common: CommonService, private usuariosService: UsuariosService, private rolesService: RolesService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.rolesService.obtenerRoles().then((listaRoles) => this.listaRoles = listaRoles).catch((err) => { alert('Ha ocurrido un error contacte con el administrador del sitio') })
    this.route.queryParams
      .subscribe(params => {
        this.nombreUsuario = params['nombre'];
        this.crearUsuario = this.nombreUsuario == null ? true : this.modificar = true;
      });
    if (this.nombreUsuario != null) {
      this.usuariosService.obtenerPorNombre(this.nombreUsuario).then((respuesta: UsuarioSistema) => {
        this.usuarioNuevo = respuesta;
        setTimeout(() => {
          if (this.usuarioNuevo.id != null) {
            this.modificarUsuario(this.usuarioNuevo);
          }
        }, 2000);
        this.common._setLoading(false);
      }).catch((err) => {
        this.common._setLoading(false);
        alert('Ha ocurrido un error, contacte con el administrador de sitio');
      })
    }
  }

  gestionarUsuarios() {
    this.usuarioNuevo.nombreUsuario = this.nombreUsuario;
    this.usuarioNuevo.correo = this.correo;
    this.usuarioNuevo.clave = this.clave;
    this.usuarioNuevo.activo = true;
    this.asignarRoles = this.listaRoles.filter(item => this.rolesSelecionados.indexOf(item.id) != -1);
    this.usuarioNuevo.roles = this.asignarRoles;
    this.usuarioNuevo.fechaCreacion = new Date();
    this.usuarioNuevo.ultimoCodigoVerificacion = "asd3asd3ghe5y65";
    this.usuariosService.guardarUsuario(this.usuarioNuevo).then((respuesta: UsuarioSistema) => {
      this.crearUsuario = false;
      this.usuarioNuevo = respuesta;
      this.cambioContrasena = false;
      this.modificar = false;
      this.router.navigateByUrl('/usuarios');
    })
      .catch((err) => alert(err.status == '409' ? 'Ya existe un usuario con ese nombre' : 'Ha ocurrido un error, contacte con el administrador del sitio'));
  }
  
  modificarUsuario(usuario: UsuarioSistema) {
    this.tituloTexto = 'Modificar ';
    this.crearUsuario = true;
    this.cambioContrasena = false;
    this.modificar = true;
    this.usuarioNuevo = usuario;
    this.nombreUsuario = usuario.nombreUsuario;
    this.idUsuario = usuario.id;
    this.correo = usuario.correo;
    this.asignarRoles = usuario.roles.filter(item => this.listaRoles.indexOf(item) == -1);
    this.rolesSelecionados = this.asignarRoles.map(({ id }) => id);
  }

  guardarModificarUsuario() {
    this.common._setLoading(true);
    this.usuarioNuevo.nombreUsuario = this.nombreUsuario;
    this.usuarioNuevo.correo = this.correo;
    this.usuarioNuevo.activo = true;
    this.asignarRoles = this.listaRoles.filter(item => this.rolesSelecionados.indexOf(item.id) != -1);
    this.usuarioNuevo.roles = this.asignarRoles;
    this.usuariosService.actualizarUsuario(this.usuarioNuevo).then((respuesta: UsuarioSistema) => {
      this.crearUsuario = false;
      this.modificar = false;
      this.cambioContrasena = false;
      this.usuarioNuevo = respuesta;
      this.common._setLoading(false);
      alert('Se guardó el usuario con éxito');
      this.router.navigateByUrl('/usuarios');
    })
      .catch((err) => alert((err.status == '409' ? 'Ya existe un usuario con ese nombre' : 'Ha ocurrido un error, contacte con el administrador del sitio')));
  }
  cancelarCrearUsuario() {
    this.router.navigateByUrl('/usuarios');
  }
}