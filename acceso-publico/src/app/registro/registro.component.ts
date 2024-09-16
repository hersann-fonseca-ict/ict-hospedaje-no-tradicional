import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, AbstractControl } from '@angular/forms';
import { MAT_STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { CommonService } from '../services/common.service';
import { PerfilService } from '../services/perfil.service';
import { UsuarioService } from '../services/usuario.service';
import { AlojamientoService } from "../services/alojamiento.service";
import { DireccionService } from "../services/direccion.service";
import { LocalizacionService } from "../services/localizacion.service";
import { TipoServicioService } from "../services/tipo-servicio.service";
import { Perfil } from '../models/perfiles';
import { TipoServicio } from '../models/tipo-servicio';
import { Provincia } from '../models/provincia';
import { Canton } from '../models/canton';
import { Distrito } from '../models/distrito';
import { UsuarioSistema } from '../models/usuario';
import { Localizacion } from '../models/localizacion';
import { Empresa } from '../models/empresa';
import { PersonaFisica } from '../models/personaFisica';
import { PersonaJuridica } from '../models/personaJuridica';
import { Alojamiento } from '../models/alojamiento';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
  providers: [
    {
      provide: MAT_STEPPER_GLOBAL_OPTIONS,
      useValue: {
        displayDefaultIndicatorType: false,
      },
    },
  ],
})
export class RegistroComponent implements OnInit {
  //Formularios
  registerFormulario: FormGroup;
  //Auxiliares
  telefonoMaskara = [/\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, '-', /\d/, /\d/,];
  cantidadIdentificacionMensaje: string;
  cantidadIdentificacionMensajeR: string;
  _masLocalizaciones: boolean;
  identificacionMax: number;
  identificacionMaxR: number;
  error?: string;
  //Selectores
  tipoIdentificacionesFisicas: string[] = [];
  provincias: Provincia[] = [];
  cantones: Canton[] = [];
  distritos: Distrito[] = [];
  tipoServicios: TipoServicio[] = [];
  tipoIdentificaciones: string[] = [];
  listaAlojamiento: any[] = [];
  //Seleccionado
  comboIdentificacion: string;
  comboIdentificacionE: string;
  //Listados
  arrayLocalizacion: Localizacion[] = [];
  //Entidades
  localizacion: Localizacion;
  perfil: Perfil;
  perfilRespaldo?: string;
  servicioSeleccionado: number;
  usuarioSistema: UsuarioSistema;
  constructor(
    private common: CommonService,
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private perfilService: PerfilService,
    private alojamientoService: AlojamientoService,
    private localizacionService: LocalizacionService,
    private direccionService: DireccionService,
    private tipoServicioService: TipoServicioService
  ) {
    this.tipoIdentificacionesFisicas = ['Número de cédula física', 'DIMEX', 'NITE'];
    this.tipoIdentificaciones = ['Física', 'Jurídica'];
  }

  stripText(event) {
    const seperator = '^([0-9])';
    const maskSeperator = new RegExp(seperator, 'g');
    let result = maskSeperator.test(event.key);
    return result;
  }

  private showAlert(code?: number, message?: string): Promise<void> {
    let type: 'err' | 'succ' | 'warn' = !code || code == 200 ? 'succ' : 'err';
    message = !message ? 'Se realizó con éxito el proceso' : message;
    return this.alert(type, message);
  }

  private alert(type: 'err' | 'succ' | 'warn', message: string, callback: Promise<void> | undefined = undefined): Promise<void> {
    return new Promise<void>(resolve => {
      alert(`${message}.`);
      if (callback) {
        callback.then(resolve);
      } else {
        resolve();
      }
    });
  }

  async cargarPerfil(): Promise<void> {
    let usuario = this.usuarioService.obtenerUsuario();
    if (usuario) {
      await this.perfilService.obtenerPerfil(usuario)
        .then(async (perfil) => {
          let tipoServicio: TipoServicio = this.tipoServicios[0];
          if (perfil) {
            tipoServicio = perfil.tipoServicio;
            this.perfilRespaldo = JSON.stringify(perfil);
            this.arrayLocalizacion = await this.localizacionService.obtenerLocalizaciones(perfil.id as number);
          }
          this.clickedTipoServicios(tipoServicio.id);
        });
    }
  }

  async loadDatas(): Promise<void> {
    return await Promise.all([
      this.direccionService.obtenerProvincias(),
      this.alojamientoService.obtenerListadoClase(),
      this.tipoServicioService.obtenerTipoServicio(),
      this.usuarioService.obtenerPorNombreUsuario(this.usuarioService.obtenerUsuario() as string)
    ]).then(async result => {
      this.provincias = result[0];
      this.listaAlojamiento = result[1];
      this.tipoServicios = result[2];
      this.usuarioSistema = result[3];
      await this.cargarPerfil();
    }).catch(ex => {
      console.error(`Ocurrió el siguiente error: ${ex && ex.message ? ex.message : "Causa desconocida"}`);
      this.error = "No se logró obtener los parámetros necesarios para el formulario,"
        + " comuniquese los administradores del sistema.";
    });
  }

  ngOnInit(): void {
    this.common._setLoading(true);
    this.loadDatas()
      .then(() => this.common._setLoading(false));
  }

  createFormEmpresa(empresa: Empresa, tipoServicio: TipoServicio): FormGroup {
    this.comboIdentificacionE = (String)(empresa.identificadorFisicoJuridico);
    return this.fb.group({
      tipoServicio: [this.servicioSeleccionado, Validators.required],
      identificacion: [(this.comboIdentificacionE == this.tipoIdentificaciones[1]) ? empresa.cedulaJuridica : '', (this.comboIdentificacionE == this.tipoIdentificaciones[1]) ? Validators.required : ""],
      comboIdentificacionEm: [empresa.identificadorFisicoJuridico, Validators.required],
      razonSocial: [empresa.razonSocial, Validators.required],
      pais: [empresa.pais, Validators.required],
      estado: [empresa.estado, Validators.required],
      ciudad: [empresa.ciudad, Validators.required],
      distrito: [empresa.distrito, Validators.required],
      codigoPostal: [empresa.codigoPostal, Validators.required],
      otrasSennas: [empresa.otrasSenas, Validators.required],
      nombre: [empresa.nombreRepresentante, Validators.required],
      identificacionR: [empresa.identificacion, Validators.required],
      tipoIdentificacion: [empresa.tipoIdentificacion, Validators.required],
      primerApellido: [empresa.primerApe, Validators.required],
      segundoApellido: [empresa.segundoApe, Validators.required],
      correo: [empresa.correo, [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      url: [empresa.url, Validators.required],
      telefono: [empresa.telefono, Validators.required],
    });
  }

  createFormPersonaFisica(personaFisica: PersonaFisica, tipoServicio: TipoServicio, correo: string | undefined): FormGroup {
    return this.fb.group({
      tipoServicio: [this.servicioSeleccionado, Validators.required],
      comboIdentificacion: [this.comboIdentificacion, Validators.required],
      tipoIdentificacion: [personaFisica.tipoIdentificacionFisica, Validators.required],
      identificacion: [personaFisica.identificacion, Validators.required],
      nombre: [personaFisica.nombre, Validators.required],
      primerApellido: [personaFisica.apellido1, Validators.required],
      segundoApellido: [personaFisica.apellido2, Validators.required],
      domicilio: [personaFisica.domicilio, Validators.required],
      correo: [correo ? correo : '', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
    });
  }

  createFormPersonaJuridica(personaJuridica: PersonaJuridica, tipoServicio: TipoServicio): FormGroup {
    return this.fb.group({
      tipoServicio: [this.servicioSeleccionado, Validators.required],
      comboIdentificacion: [this.comboIdentificacion, Validators.required],
      identificacion: [personaJuridica.cedJuridico, Validators.required],
      identificacionR: [personaJuridica.identificacion, Validators.required],
      tipoIdentificacionR: [personaJuridica.tipoIdentificacion, Validators.required],
      razonSocial: [personaJuridica.nombreComercial, Validators.required],
      nombre: [personaJuridica.nombreRepresentante, Validators.required],
      primerApellido: [personaJuridica.apellido1Representante, Validators.required],
      segundoApellido: [personaJuridica.apellido2Representante, Validators.required],
      domicilio: [personaJuridica.domicilio, Validators.required],
      correo: [personaJuridica.correo, [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
    });
  }

  createFormAlojamiento(): FormGroup {
    return this.fb.group({
      alojamiento: ['', Validators.required],
      provincia: ['', Validators.required],
      canton: ['', Validators.required],
      distrito: ['', Validators.required],
      direccion: ['', Validators.required],
      numHabitaciones: ['', Validators.required],
      numMaxHuespedes: ['', Validators.required],
      numCamas: ['', Validators.required],
      correo: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      telefono: ['', Validators.required],
      url1: ['', Validators.required],
      url2: [],
      url3: [],
    });
  }

  createForm(perfil: Perfil, seleccionado: number) {
    if (this.registerFormulario) {
      this.registerFormulario.reset();
    }
    let formGroup: any;
    if (perfil.empresa != null && seleccionado == 2) {
      formGroup = { registerForm: this.createFormEmpresa(perfil.empresa, perfil.tipoServicio) };
    } else {
      let registerForm: FormGroup;
      if (perfil.personaFisica != null) {
        registerForm = this.createFormPersonaFisica(perfil.personaFisica, perfil.tipoServicio, perfil.correo)
      } else if (perfil.personaJuridica != null) {
        registerForm = this.createFormPersonaJuridica(perfil.personaJuridica, perfil.tipoServicio)
      } else {
        registerForm = this.fb.group({});
      }
      formGroup = { registerForm, alojamientoForm: this.createFormAlojamiento() };
      this.localizacion = new Localizacion();
      this._masLocalizaciones = true;
    }
    this.registerFormulario = this.fb.group(formGroup, { updateOn: 'change' });
  }

  get f() {
    return this.registerForm?.controls;
  }

  get d() {
    return this.alojamientoForm?.controls;
  }

  get registerForm(): FormGroup | null {
    return this.registerFormulario?.get('registerForm') as FormGroup;
  }

  get alojamientoForm(): FormGroup | null {
    return this.registerFormulario?.get('alojamientoForm') as FormGroup;
  }

  clickedTipoServicios(seleccionado: number) {
    let tipoSeleccionado = this.tipoServicios.find(t => t.id == seleccionado);
    this.servicioSeleccionado = seleccionado;
    if (tipoSeleccionado) {
      let perfilRespaldo: Perfil | undefined = this.perfilRespaldo ? JSON.parse(this.perfilRespaldo) : undefined;
      if (perfilRespaldo) {
        this.perfil = perfilRespaldo;
        if (perfilRespaldo && tipoSeleccionado.codigo == "HNT" && (this.perfil.personaJuridica || this.perfil.personaFisica)) {
          this.clickedTipoID(this.perfil.personaFisica ? this.tipoIdentificaciones[0] : this.tipoIdentificaciones[1], false);
          return;
        } else if (perfilRespaldo && tipoSeleccionado.codigo == "EI" && this.perfil.empresa) {
          this.clickedTipoIDXEmpresa(this.perfil.empresa.identificadorFisicoJuridico ? this.perfil.empresa.identificadorFisicoJuridico : 'Física');
          this.createForm(this.perfil, tipoSeleccionado.id);
          this.clickedTipoIDFisicaXJur( this.perfil.empresa.tipoIdentificacion, true , false);
          return;
        }
      }
      if (perfilRespaldo == null || (!this.perfil.personaJuridica || !this.perfil.personaFisica || !this.perfil.empresa)) {
        this.perfil = new Perfil(
          tipoSeleccionado,
          this.usuarioSistema,
          this.perfil ? this.perfil.correo : '',
          this.perfil ? this.perfil.fechaCreacion : new Date(),
          this.perfil ? this.perfil.id : undefined
        );
        if (tipoSeleccionado.codigo == "HNT" && (!this.perfil.personaJuridica || !this.perfil.personaFisica)) {
          this.perfil.personaFisica = new PersonaFisica(this.tipoIdentificacionesFisicas[0]);
          this.clickedTipoID(this.tipoIdentificaciones[0], false);
        } else {
          this.perfil.empresa = new Empresa();
          this.createForm(this.perfil, tipoSeleccionado.id);
        }
      }
    }
  }

  clickedTipoID(tipoSeleccionado: string, isFromSelected: boolean = true) {
    if (tipoSeleccionado) {
      this.comboIdentificacionE = "";
      this.comboIdentificacion = tipoSeleccionado;
      if (isFromSelected) {
        let perfilRespaldo: Perfil | undefined = this.perfilRespaldo ? JSON.parse(this.perfilRespaldo) : undefined;
        if (perfilRespaldo && tipoSeleccionado == this.tipoIdentificaciones[0] && perfilRespaldo.personaFisica
          || tipoSeleccionado == this.tipoIdentificaciones[1] && perfilRespaldo?.personaJuridica) {
          this.perfil = perfilRespaldo;
        } else {
          this.perfil = new Perfil(
            this.perfil.tipoServicio,
            this.usuarioSistema,
            this.perfil.correo,
            this.perfil.fechaCreacion,
            this.perfil.id
          );
          if (tipoSeleccionado == this.tipoIdentificaciones[0]) {
            this.perfil.personaFisica = new PersonaFisica(this.tipoIdentificacionesFisicas[0]);
          } else {
            this.perfil.personaJuridica = new PersonaJuridica();
          }
        }
      }
      this.createForm(this.perfil, 1);
      if (tipoSeleccionado == this.tipoIdentificaciones[0]) {
        this.clickedTipoIDFisica(this.perfil.personaFisica?.tipoIdentificacionFisica, false);
      } else {
        this.clickedTipoIDJuridica(false);
        this.clickedTipoIDFisicaXJur(this.perfil.personaJuridica?.tipoIdentificacion, false);
      }
    }
  }

  clickedTipoIDXEmpresa(tipoSeleccionado: string) {
    const identificacion = this.registerForm?.get('identificacion');
    if (tipoSeleccionado) {
      this.comboIdentificacionE = tipoSeleccionado;
      if (tipoSeleccionado == this.tipoIdentificaciones[0]) {
        identificacion?.setValue('1000000000');
      } else {
        identificacion?.setValue('');
      }
    }
    this.clickedTipoIDJuridica(false);
  }

  clickedTipoIDFisica(tipoSeleccionado?: string, isFromSelected: boolean = true) {
    const identificacion = this.registerForm?.get('identificacion');
    if (identificacion) {
      if (isFromSelected) {
        let perfilRespaldo: Perfil | undefined = this.perfilRespaldo ? JSON.parse(this.perfilRespaldo) : undefined;
        if (perfilRespaldo && tipoSeleccionado == perfilRespaldo.personaFisica?.tipoIdentificacionFisica) {
          identificacion.setValue(perfilRespaldo.personaFisica?.identificacion);
        } else {
          identificacion.setValue(undefined);
        }
      }
      if (tipoSeleccionado == this.tipoIdentificacionesFisicas[0]) {
        identificacion.setValidators(Validators.compose([
          Validators.required,
          Validators.minLength(9),
          Validators.pattern('^[1-9]{1}[0-9]+')
        ]));
        this.cantidadIdentificacionMensaje = 'de 9 dígitos';
        this.identificacionMax = 9;
      } else if (tipoSeleccionado == this.tipoIdentificacionesFisicas[1]) {
        identificacion.setValidators(Validators.compose([
          Validators.required,
          Validators.minLength(12),
          Validators.pattern('^[A-Za-z1-9]{1}[A-Za-z0-9]+')
        ]));
        this.identificacionMax = 12;
        this.cantidadIdentificacionMensaje = 'de 12 dígitos y no puede iniciar con 0';
      }
      else if (tipoSeleccionado == this.tipoIdentificacionesFisicas[2]) {
        identificacion.setValidators(Validators.compose([
          Validators.required,
          Validators.minLength(12),
          Validators.pattern('^[A-Za-z1-9]{1}[A-Za-z0-9]+')
        ]));
        this.identificacionMax = 12;
        this.cantidadIdentificacionMensaje = 'de 12 dígitos y no puede iniciar con 0';
      }
      identificacion.updateValueAndValidity();
    }
  }

  clickedTipoIDFisicaXJur(tipoSeleccionado?: string, isEi: boolean = true, isFromSelected: boolean = true) {
    const identificacion = this.registerForm?.get('identificacionR');
    if (identificacion) {
      if (isFromSelected) {
        let perfilRespaldo: Perfil | undefined = this.perfilRespaldo ? JSON.parse(this.perfilRespaldo) : undefined;
        if (perfilRespaldo && ( (!isEi && tipoSeleccionado == perfilRespaldo.personaJuridica?.tipoIdentificacion) || (isEi && tipoSeleccionado == perfilRespaldo.empresa?.tipoIdentificacion)) ) {
          (isEi)
              ? identificacion.setValue(perfilRespaldo.empresa?.identificacion) 
              : identificacion.setValue(perfilRespaldo.personaJuridica?.identificacion) ;
        } else {
          identificacion.setValue(undefined);
        }
      }
      if (tipoSeleccionado == this.tipoIdentificacionesFisicas[0]) {
        identificacion.setValidators(Validators.compose([
          Validators.required,
          Validators.minLength(9),
          Validators.pattern('^[1-9]{1}[0-9]+')
        ]));
        this.cantidadIdentificacionMensajeR = 'de 9 dígitos';
        this.identificacionMaxR = 9;
      } else if (tipoSeleccionado == this.tipoIdentificacionesFisicas[1]) {
        identificacion.setValidators(Validators.compose([
          Validators.required,
          Validators.minLength(12),
          Validators.pattern('^[A-Za-z1-9]{1}[A-Za-z0-9]+')
        ]));
        this.identificacionMaxR = 12;
        this.cantidadIdentificacionMensajeR = 'de 12 dígitos y no puede iniciar con 0';
      }
      else if (tipoSeleccionado == this.tipoIdentificacionesFisicas[2]) {
        identificacion.setValidators(Validators.compose([
          Validators.required,
          Validators.minLength(12),
          Validators.pattern('^[A-Za-z1-9]{1}[A-Za-z0-9]+')
        ]));
        this.identificacionMax = 12;
        this.cantidadIdentificacionMensaje = 'de 12 dígitos y no puede iniciar con 0';
      }
      identificacion.updateValueAndValidity();
    }
  }

  clickedTipoIDJuridica(isFromSelected: boolean = true) {
    const identificacion = this.registerForm?.get('identificacion');
    if (identificacion) {
      if (isFromSelected) {
        identificacion.setValue(undefined);
      }
      identificacion.setValidators(Validators.compose([
        Validators.required,
        Validators.minLength(10),
        Validators.pattern('^[1-9]{1}[0-9]+')
      ]));
      this.cantidadIdentificacionMensaje = 'de 10 dígitos';
      this.identificacionMax = 10;
      identificacion.updateValueAndValidity();
    }
  }

  clickedAlojamiento(alojamiento: Alojamiento) {
    if (alojamiento) {
      this.localizacion.alojamiento = alojamiento;
      this.d?.alojamiento.setValue(alojamiento.id);
    }
  }

  clickedProvincia(seleccionada: number) {
    this.localizacion.provincia = this.provincias.find(p => p.id == seleccionada);
    if (this.localizacion.provincia) {
      this.direccionService.obtenerCantones(seleccionada).then((data) => {
        this.cantones = data;
      });
    }
  }

  clickedCanton(seleccionado: number) {
    this.localizacion.canton = this.cantones.find(p => p.id == seleccionado);
    if (this.localizacion.canton) {
      this.direccionService.obtenerDistritos(seleccionado).then((data) => {
        this.distritos = data;
      });
    }
  }

  clickedDistrito(seleccionado: number) {
    this.localizacion.distrito = this.distritos.find(p => p.id == seleccionado);
  }

  noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').toString().trim().length == 0;
    const isValid = !isWhitespace;
    return isValid ? null : { whitespace: true };
  }

  getDatosLocalizacion(localizacion: Localizacion): Localizacion {
    localizacion.direccion = this.d?.direccion?.value;
    localizacion.numHabitacion = this.d?.numHabitaciones?.value;
    localizacion.numMaxHuesped = this.d?.numMaxHuespedes?.value;
    localizacion.numCamas = this.d?.numCamas?.value;
    localizacion.urlInmueble1 = this.d?.url1?.value;
    localizacion.urlInmueble2 = this.d?.url2?.value;
    localizacion.urlInmueble3 = this.d?.url3?.value;
    localizacion.correo = this.d?.correo?.value;
    localizacion.telefono = this.d?.telefono?.value.toString().replace(/-/g, '');
    return localizacion;
  }

  guardarLocalizacion() {
    this.arrayLocalizacion.push(
      this.getDatosLocalizacion(this.localizacion)
    );
    this.localizacion = new Localizacion();
    this.alojamientoForm?.reset();
  }

  private deleteAlojamiento(id?: number, code?: number) {
    let type: 'err' | 'succ' | 'warn' = 'err', message = "Ocurrio un error al eliminar la localización";
    if (code == 200) {
      type = 'succ';
      message = 'Se elimino satisfactoriamente la localización';
    }
    if (id) {
      this.arrayLocalizacion = this.arrayLocalizacion.filter(l => l.id !== id);
    }
    this.alert(type, message);
  }

  async eliminarAlojamiento(item: Localizacion) {
    if (this.arrayLocalizacion.length == 1) {
      this.showAlert(400, 'No puede eliminar el alojamiento ya que necesita mínimo uno.');
      return;
    }
    if (item.id) {
      await this.localizacionService
        .eliminarLocalizacion(item.id)
        .then(_ => this.deleteAlojamiento(item.id, 200))
        .catch(err => this.deleteAlojamiento(item.id, err ? err.status : undefined));
    } else {
      this.deleteAlojamiento(item.id, 200);
    }
  }

  actualizarInmueble(loc: Localizacion) {
    loc.form = this.fb.group({
      provincia: [loc.provincia, Validators.required],
      canton: [loc.canton, Validators.required],
      distrito: [loc.distrito, Validators.required],
      direccion: [loc.direccion, Validators.required],
      numHabitaciones: [loc.numHabitacion, Validators.required],
      numMaxHuespedes: [loc.numMaxHuesped, Validators.required],
      numCamas: [loc.numCamas, Validators.required],
      correo: [loc.correo, [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      telefono: [loc.telefono, Validators.required],
      url1: [loc.urlInmueble1, Validators.required],
      url2: [loc.urlInmueble2],
      url3: [loc.urlInmueble3],
    }, { updateOn: 'change' });
    loc.form.controls.provincia.disable();
    loc.form.controls.canton.disable();
    loc.form.controls.distrito.disable();
    loc.form.controls.numHabitaciones.disable();
    loc.form.controls.numMaxHuespedes.disable();
    loc.form.controls.numCamas.disable();
    loc.form.controls.telefono.disable();
    if (!loc.rechazar && loc.id) {
      loc.form.controls.direccion.disable();
      loc.form.controls.url1.disable();
      loc.form.controls.url2.disable();
      loc.form.controls.url3.disable();
    }
    loc.editar = true;
  }

  guardarInmueble(loc: Localizacion) {
    //loc.provincia = loc.form?.get('provincia')?.value;
    //loc.canton = loc.form?.get('canton')?.value;
    //loc.distrito = loc.form?.get('distrito')?.value;
    //loc.numHabitacion = loc.form?.get('numHabitaciones')?.value;
    //loc.numMaxHuesped = loc.form?.get('numMaxHuespedes')?.value;
    //loc.numCamas = loc.form?.get('numCamas')?.value;
    loc.correo = loc.form?.get('correo')?.value;
    //loc.telefono = loc.form?.get('telefono')?.value;
    if (loc.rechazar || !loc.id) {
      loc.direccion = loc.form?.get('direccion')?.value;
      loc.urlInmueble1 = loc.form?.get('url1')?.value;
      loc.urlInmueble2 = loc.form?.get('url2')?.value;
      loc.urlInmueble3 = loc.form?.get('url3')?.value;
    }
    loc.editar = false;
    loc.form = undefined;
  }

  cancelarInmueble(loc: Localizacion) {
    loc.editar = false;
    loc.form = undefined;
  }

  disabledFinalizar(): boolean {
    return this.arrayLocalizacion.filter(e => e.editar).length > 0;
  }

  getControls(item: Localizacion): { [key: string]: AbstractControl } | undefined {
    return item.form?.controls;
  }

  disabledAgregarLocalizaciones(): boolean {
    return this._masLocalizaciones || this.arrayLocalizacion.length < 1;
  }

  noMasLocalizaciones() {
    this._masLocalizaciones = false;
    this.alojamientoForm?.disable();
  }

  masLocalizaciones() {
    this._masLocalizaciones = true;
  }

  siguientePerfil() {
    if (this.perfil.tipoServicio.codigo == "HNT") {
      this._masLocalizaciones = true;
      this.alojamientoForm?.enable();
    }
  }

  regresarResumen() {
    if (this.perfil.tipoServicio.codigo == "HNT") {
      this._masLocalizaciones = true;
      this.alojamientoForm?.enable();
    }
  }

  getDatosEmpresa(empresa: Empresa): Empresa {
    empresa.razonSocial = this.f?.razonSocial?.value;
    empresa.pais = this.f?.pais?.value;
    empresa.estado = this.f?.estado?.value;
    empresa.ciudad = this.f?.ciudad?.value;
    empresa.codigoPostal = this.f?.codigoPostal?.value;
    empresa.otrasSenas = this.f?.otrasSennas?.value;
    empresa.nombreRepresentante = this.f?.nombre?.value;
    empresa.primerApe = this.f?.primerApellido?.value;
    empresa.segundoApe = this.f?.segundoApellido?.value;
    empresa.correo = this.f?.correo?.value;
    empresa.tipoIdentificacion = this.f?.tipoIdentificacion?.value;
    empresa.identificacion = this.f?.identificacionR?.value;
    (this.f?.comboIdentificacionEm?.value == this.tipoIdentificaciones[1]) ? empresa.cedulaJuridica = this.f?.identificacion?.value : empresa.cedulaJuridica = null;
    empresa.distrito = this.f?.distrito?.value;
    empresa.identificadorFisicoJuridico = this.f?.comboIdentificacionEm?.value;
    empresa.url = this.f?.url?.value;
    empresa.telefono = this.f?.telefono?.value.toString().replace(/-/g, '');
    empresa.aprobar = false;
    empresa.desafiliar = false;
    empresa.rechazar = false;
    return empresa;
  }

  getDatosPersonaFisica(personaFisica: PersonaFisica): PersonaFisica {
    personaFisica.fechaCreacion = new Date();
    personaFisica.tipoIdentificacionFisica = this.f?.tipoIdentificacion?.value;
    personaFisica.identificacion = this.f?.identificacion?.value.toString().replace(/-/g, '');
    personaFisica.nombre = this.f?.nombre?.value;
    personaFisica.apellido1 = this.f?.primerApellido?.value;
    personaFisica.apellido2 = this.f?.segundoApellido?.value;
    personaFisica.domicilio = this.f?.domicilio?.value;
    return personaFisica;
  }

  getDatosPersonaJuridica(personaJuridica: PersonaJuridica): PersonaJuridica {
    personaJuridica.fechaCreacion = new Date();
    personaJuridica.cedJuridico = this.f?.identificacion?.value.toString().replace(/-/g, '');
    personaJuridica.tipoIdentificacion = this.f?.tipoIdentificacionR?.value;
    personaJuridica.identificacion = this.f?.identificacionR?.value;
    personaJuridica.nombreComercial = this.f?.razonSocial?.value;
    personaJuridica.nombreRepresentante = this.f?.nombre?.value;
    personaJuridica.apellido1Representante = this.f?.primerApellido?.value;
    personaJuridica.apellido2Representante = this.f?.segundoApellido?.value;
    personaJuridica.domicilio = this.f?.domicilio?.value;
    personaJuridica.correo = this.f?.correo?.value;
    return personaJuridica;
  }

  private preparedToSend(l: Localizacion, perfil: Perfil) {
    l.perfil = perfil;
    l.form = undefined;
    return l;
  }

  guardarPerfil() {
    if (this.servicioSeleccionado == 2 && this.perfil.empresa) {
      this.perfil.empresa = this.getDatosEmpresa(this.perfil.empresa);
    } else if (this.perfil.personaFisica) {
      this.perfil.personaFisica = this.getDatosPersonaFisica(this.perfil.personaFisica);
    } else if (this.perfil.personaJuridica) {
      this.perfil.personaJuridica = this.getDatosPersonaJuridica(this.perfil.personaJuridica);
    }
    this.perfil.correo = this.f?.correo?.value;
  }

  async finalizar(stepper: any) {
    if (this.perfil.empresa == null && this.arrayLocalizacion?.length <= 0) {
      this.showAlert(400, 'Debe agregar mínimo un Inmueble');
    } else {
      this.common._setLoading(true);
      try {
        this.perfil = await this.perfilService.guardarPerfil(this.perfil);
        this.perfilRespaldo = JSON.stringify(this.perfil);
        if (this.servicioSeleccionado == 1) {
          try {
            let listadoSinForm = this.arrayLocalizacion.map(l => this.preparedToSend(l, this.perfil));
            this.arrayLocalizacion = await this.localizacionService.guardarListado(listadoSinForm);
            this.showAlert();
            stepper.previous();
            stepper.previous();
          } catch (err) {
            console.error(err);
            this.showAlert(err ? err.status : 400, err ? 'Ocurrio un error al guardar el listado' : undefined);
          }
        } else {
          this.arrayLocalizacion = [];
          this.showAlert();
          stepper.previous();
          stepper.previous();
        }
      } catch (err) {
        if (err && err.status == 409) {
          this.showAlert(409, 'Ya se ha registrado un perfil con esta identificación');
        } else {
          this.showAlert(500, 'Ocurrio un error al guardar el perfil');
        }
      }
      this.common._setLoading(false);
    }
  }
}
