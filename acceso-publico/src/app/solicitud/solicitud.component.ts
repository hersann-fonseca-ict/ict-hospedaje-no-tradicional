import { Component, OnInit } from '@angular/core';
import { Perfil } from '../models/perfiles';
import { TipoServicio } from '../models/tipo-servicio';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { PerfilService } from '../services/perfil.service';
import { UsuarioService } from '../services/usuario.service';
import { NotificacionService } from '../services/notificacion.service';
import { SolicitudService } from '../services/solicitud.service';
import { TipoServicioService } from '../services/tipo-servicio.service';
import { UsuarioSistema } from '../models/usuario';
import { Solicitud, Beneficio } from '../models/solicitud';

@Component({
  selector: 'app-solicitud',
  templateUrl: './solicitud.component.html',
  styleUrls: ['./solicitud.component.css']
})
export class SolicitudComponent implements OnInit {
  nuevoPerfil: Perfil;
  nombre: string = '';
  nombreComercial: string = '';
  apellido1: string = '';
  apellido2: string = '';
  telefono: string = '';
  canton: string = '';
  distrito: string = '';
  direccion: string = '';
  provincia: string = '';
  codigoAirbnb: string = '';
  descripcion: string = '';
  listaTipoSercicio: TipoServicio[];
  listaTipoSercicioActions: TipoServicio[];
  necesitaCodigo: boolean = false;
  estadoAbierta: boolean = false;
  estadoNuevo: boolean = false;

  estadoAprobada: boolean = false;
  registerFormulario: FormGroup;

  //tipoServicio: string='';
  nombreCodigoServicio: string = '';
  listaActividades: Array<{
    tipoServicio: string,
    codig: string,
    observaciones: string
  }> = [];
  listaBeneficios: Beneficio[]
  tipoServicioSelect: number;
  tipoServicio: TipoServicio = new TipoServicio();
  tipoId: string = '';
  editar: boolean = false;
  identificacion: string = '';
  tipoIdentificaciones: string[] = ['Física', 'Jurídica'];
  tipoIdentificacioFisica: string = '';
  habilitarTipoFisica: boolean = true;
  estadoRechazado: boolean = false;
  habilitarIdentificacion: boolean = true;
  mostrarEnviar: boolean = true;
  identificacionComercial: number;
  listaUsuarios: UsuarioSistema[];
  habilitarIdentificacioComercial: boolean = true;
  tipoIdentificacionesFisicas: string[] = ['Cédula Identidad', 'Dimex', 'Pasaporte'];
  tipoIdentificacion: string = '';
  afectaciones: string = '';
  solicitud: Solicitud = new Solicitud();
  solicitudTemp: Solicitud = new Solicitud();

  listaSolicitud: Array<Solicitud> = [];
  listaSolicitudTemp: Array<Solicitud> = [];
  submitted = false;
  estadoSolicitud: string = 'nueva';
  existePerfil: boolean;
  justificacion: string = '';
  fileList: FileList;
  enviado: boolean = false;
  textoExiste: string = '';
  listaArchivos: string[];

  constructor(
    private fb: FormBuilder,
    private perfilService: PerfilService,
    private solicitudService: SolicitudService,
    private usuarioService: UsuarioService,
    private notificacionService: NotificacionService,
    private tipoServicioService: TipoServicioService
  ) {

    this.createForm();
    this.perfilService.obtenerPerfil(this.usuarioService.obtenerUsuario() as string).then((respuesta: Perfil) => {
      if (respuesta != null) {
        this.existePerfil = true;
        this.nuevoPerfil = respuesta;
        this.editar = true;
        /* this.telefono = this.nuevoPerfil.telefono; */
        if (this.nuevoPerfil.personaFisica != null) {
          this.tipoIdentificacion = 'Física';
          this.nombre = this.nuevoPerfil.personaFisica.nombre;
          this.identificacion = this.nuevoPerfil.personaFisica.identificacion;
          this.apellido1 = this.nuevoPerfil.personaFisica.apellido1;
          this.apellido2 = this.nuevoPerfil.personaFisica.apellido2;

        } else {
          this.tipoIdentificacion = 'Jurídica';
          this.nombre = this.nuevoPerfil.personaJuridica.nombreRepresentante;
          // this.identificacion = this.nuevoPerfil.personaJuridica.identificacionRepresentante;
          // this.identificacionComercial = this.nuevoPerfil.personaJuridica.identificacionComercial;
          this.nombreComercial = this.nuevoPerfil.personaJuridica.nombreComercial;
          this.apellido1 = this.nuevoPerfil.personaJuridica.apellido1Representante;
          this.apellido2 = this.nuevoPerfil.personaJuridica.apellido2Representante;
        }
        /*   this.provincia = this.nuevoPerfil.provincia;
          this.canton = this.nuevoPerfil.canton;
          this.distrito = this.nuevoPerfil.distrito;
          this.direccion = this.nuevoPerfil.direccion; */

        // this.tipoServicioSelect = this.nuevoPerfil.tipoServicio;
        this.codigoAirbnb = this.nuevoPerfil.codigoTipoServicio;
        this.necesitaCodigo = this.tipoServicio.necesitaCodigo;
        this.solicitudTemp = this.nuevoPerfil.solicitud[0] != undefined ? this.nuevoPerfil.solicitud[0] : null;


        this.estadoSolicitud = this.solicitudTemp != null ? this.solicitudTemp.estadoSolicitud : 'nueva';

        this.afectaciones = this.solicitudTemp != null ? this.solicitudTemp.observaciones : '';
        this.tipoServicioService.obtenerTipoServicio().then(data => {
          this.listaTipoSercicio = data;
          this.listaTipoSercicioActions = data;
          //  this.tipoServicio = this.listaTipoSercicioActions.find(x => x.id == this.nuevoPerfil.tipoServicio);

          this.listaActividades = [];
          this.listaActividades.push({
            tipoServicio: this.tipoServicio.nombre,
            codig: this.codigoAirbnb,
            observaciones: this.descripcion
          });
        });
        if (this.nuevoPerfil.solicitud != null) {

          this.obtenerlistaArchivos(this.nuevoPerfil.solicitud[0].id, this.nuevoPerfil.solicitud[0].identificacionRepresentante);
          if (this.nuevoPerfil.solicitud[0].estadoSolicitud == 'APROBADA') {
            this.listaBeneficios = this.nuevoPerfil.solicitud != null ? this.nuevoPerfil.solicitud[0].beneficios : [];
          }
          if (this.nuevoPerfil.solicitud[0].estadoSolicitud == 'RECHAZADA') {
            this.justificacion = this.nuevoPerfil.solicitud[0].justificacion;
          }
        }
      } else {
        this.estadoSolicitud = 'nueva';
        this.solicitudTemp = null;
        this.textoExiste = 'Debe crear su perfil para poder enviar una solicitud';

      }


    });

  }
  obtenerlistaArchivos(id, identificacion) {
    this.solicitudService.listarDocumentos('archivos/' + identificacion + '/' + id).then((respuesta) => {
      this.listaArchivos = respuesta;
    }).catch((err) => this.listaArchivos = null);
  }

  createForm() {
    this.registerFormulario = this.fb.group({
      afectacionForm: this.fb.group({
        afectaciones: ['', Validators.required]
      })
    });
  }
  get a() {
    return (<FormGroup>this.registerFormulario.get('afectacionForm')).controls;
  }
  get afectacionForm() {
    return this.registerFormulario.get('afectacionForm');
  }

  ngOnInit(): void {

  }
  enviarSolicitud() {
    this.submitted = true;
    if (this.afectacionForm.invalid) {

      return;
    }
    this.solicitud.observaciones = this.afectaciones;
    this.solicitud.nombreComercial = this.nuevoPerfil.personaJuridica != null ? this.nuevoPerfil.personaJuridica.nombreComercial : 'no aplica';
    // this.solicitud.identificacionComercial = this.nuevoPerfil.personaJuridica != null ? this.nuevoPerfil.personaJuridica.identificacionComercial : 0;
    //  this.solicitud.identificacionRepresentante = this.nuevoPerfil.personaJuridica != null ? Number(this.nuevoPerfil.personaJuridica.identificacionRepresentante) : Number(this.nuevoPerfil.personaFisica.identificacion);
    this.solicitud.distrito = this.distrito;
    this.solicitud.canton = this.canton;
    this.solicitud.provincia = this.provincia;
    this.solicitud.direccion = this.direccion;
    this.solicitud.telefono = this.telefono;
    this.solicitud.estadoSolicitud = "ABIERTA";
    this.solicitud.fechaCreacion = new Date();
    this.solicitud.codigoTipoServicio = this.codigoAirbnb;
    this.solicitud.nombreRepresentante = this.nuevoPerfil.personaJuridica != null ? this.nuevoPerfil.personaJuridica.nombreRepresentante : this.nuevoPerfil.personaFisica.nombre;
    this.solicitud.apellido1Representante = this.nuevoPerfil.personaJuridica != null ? this.nuevoPerfil.personaJuridica.apellido1Representante : this.nuevoPerfil.personaFisica.apellido1;
    this.solicitud.apellido2Representante = this.nuevoPerfil.personaJuridica != null ? this.nuevoPerfil.personaJuridica.apellido2Representante : this.nuevoPerfil.personaFisica.apellido2;

    //this.solicitud.tipoServicio = this.nuevoPerfil.tipoServicio;
    this.listaSolicitud = [];
    this.listaSolicitud.push(this.solicitud);
    this.nuevoPerfil.solicitud = this.listaSolicitud;
    this.nuevoPerfil.fechaCreacion = new Date();
    this.perfilService.guardarPerfil(this.nuevoPerfil).then((respuesta: Perfil) => {
      let numeroFormulario = "F00" + respuesta.solicitud[0].id;
      alert('Su solicitud de beneficios se registró correctamente con el número de solicitud ' + numeroFormulario + ', se enviará una notificación a su correo electrónico');
      this.enviado = true;
      this.notificacionService.enviarNotificacion(numeroFormulario);
      this.cagarDocumentos(respuesta.solicitud[0].id);
    })
      .catch((err) => alert('Ha ocurrido un error, contacte con el administrador del sitio' + err.status));
  }

  change(event: any) {
    this.fileList = event.target.files;
  }
  cagarDocumentos(id: number) {
    /*  let identificacion = this.nuevoPerfil.personaFisica != null ? this.nuevoPerfil.personaFisica.identificacion : this.nuevoPerfil.personaJuridica.identificacionRepresentante;
     let ruta = identificacion + '/' + id; */
    if (this.fileList != undefined && this.fileList.length > 0) {
      for (var i = 0; i < this.fileList.length; i++) {

        let file: File = this.fileList[i];
        /* this.solicituService.subirDocumentos(file, ruta).then((respuesta: any) => {
          alert(respuesta == true ? 'Se ha adjuntado el documento: ' + file.name : 'Ha ocurrido un error, contacte con el administrador del sitio');
        })
          .catch((err) => alert('Ha ocurrido un error al adjuntar los documentos, contacte con el administrador del sitio'));/*/
      }
    }
  }
}
