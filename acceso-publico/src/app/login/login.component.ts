import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { UsuarioSistema } from '../models/usuario';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonService } from '../services/common.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private common: CommonService, private loginService: LoginService, private fb: FormBuilder, private route: ActivatedRoute,
    private router: Router) {
    this.createForm();
  }
  loading = false;
  fieldTextType: boolean = false;
  resgistrarse: string = 'no';
  repiteClave: string = '';
  nuevoUsuarioFormulario: FormGroup;
  codigoForm: FormGroup;
  cambioForm: FormGroup;
  nombreUsuarioLogin: string = '';
  contrasenaLogin: string = '';
  estaAutenticado: boolean = false;
  credencialesInvalidas: boolean = false;
  cambiarcontrasena: boolean = false;
  nuevoUsuario: UsuarioSistema = new UsuarioSistema();
  usuarioCambio: UsuarioSistema = new UsuarioSistema();
  nombreUsuario: string = '';
  codigo: string = '';
  nuevaContrasena: string = '';

  ngOnInit(): void {
    this.estaAutenticado = this.loginService.sessionValida();
    this.common.loadingService.subscribe(data => {
      this.loading = data;
    });
  }
  createForm() {
    this.nuevoUsuarioFormulario = this.fb.group({
      //nombreUsuario: ['', Validators.required],
      email: [null, [Validators.required, Validators.email]],
      contrasena: ['', Validators.required]
    });
    this.codigoForm = this.fb.group({
      nombreUsuarioCodigo: ['', Validators.required]

    });
    this.cambioForm = this.fb.group({
      nombreUsuarioCambio: [null, [Validators.required, Validators.email]],
      codigo: ['', Validators.required],
      nuevaContrasena: ['', Validators.required]


    });
  }
  pantallaRegistro() {
    this.resgistrarse = 'si';
    this.nombreUsuarioLogin = '';
    this.contrasenaLogin = '';
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }
  registrarUsuario() {
    this.common._setLoading(true);
    this.nuevoUsuario.fechaCreacion = new Date();
    this.nuevoUsuario.activo = true;
    this.nuevoUsuario.nombreUsuario = this.nuevoUsuario.correo;
    this.loginService.registrarUsuario(this.nuevoUsuario).then((respuesta: UsuarioSistema) => {
      this.common._setLoading(false);
      alert('Se registró satisfactoriamente, se envió una notificación a su correo y el código de verificación es: ' + respuesta.ultimoCodigoVerificacion);
      this.nombreUsuarioLogin = this.nuevoUsuario.nombreUsuario;
      this.resgistrarse = 'no';
    })
      .catch((err) => {
        if (err.status == "409") {
          this.common._setLoading(false);
          alert("Ya existe un usuario con ese nombre");
        } else if (err.status == '200') {
          this.common._setLoading(false);
          alert("Se registró satisfactoriamente");
        } else {
          this.common._setLoading(false);
          alert("Ha ocurrido un error, contacte con el administrador del sitio");
        }
      })
  }

  iniciarSesion() {
    this.common._setLoading(true);
    this.loginService.iniciarSession(this.nombreUsuarioLogin, this.contrasenaLogin);

  }
  olvidoContrasena() {
    this.resgistrarse = 'cambio';
    this.nuevoUsuario.correo = '';
    this.nuevoUsuario.nombreUsuario = '';
    this.nuevoUsuario.clave = '';
    this.contrasenaLogin = '';
    this.nuevaContrasena = '';
  }
  volverLogin() {
    this.resgistrarse = 'no';
    this.nuevoUsuario.correo = '';
    this.nuevoUsuario.nombreUsuario = '';
    this.nuevoUsuario.clave = '';
    this.contrasenaLogin = '';
  }
  pedirCodigo() {
    this.resgistrarse = 'solicitud';
    this.nombreUsuario = '';
  }
  solicitarCodigo() {
    this.common._setLoading(true); 
    this.loginService.solicitarCodigo(this.nombreUsuario).then((respuesta) => {
      this.olvidoContrasena();
      this.common._setLoading(false); 
      alert('Se ha enviado el código para cambio de contraseña a su correo');
    }).catch((err) => {
      if (err.status == '200') {
        this.common._setLoading(false); 
        this.olvidoContrasena();
        alert('Se ha enviado el código de verificación para cambio de contraseña a su correo electrónico');
      } else {
        this.common._setLoading(false); 
        alert('Ha ocurrido un error, contacte con el administrador del sitio');
      }
    });

  }
  cambiarContrasena() {
    this.common._setLoading(true);
    this.loginService.cambiarContrasena(this.nombreUsuario, this.codigo, this.nuevaContrasena).then((respuesta) => {
      this.volverLogin();
      this.common._setLoading(false);
      alert('Se ha asignado una nueva clave, se envió la notificación a su correo');
    }).catch((err) => {
      if (err.status == '200') {
        this.volverLogin();
        this.common._setLoading(false);
        alert('Se ha asignado una nueva clave, se envió la notificación a su correo');
      } else if (err.status == '400') {
        this.common._setLoading(false);
        alert('El código que ingreso no es valido, solicite un código valido');
      } else {
        this.common._setLoading(false);
        alert('Ha ocurrido un error, contacte con el administrador del sitio');
      }
    });

  }
}
