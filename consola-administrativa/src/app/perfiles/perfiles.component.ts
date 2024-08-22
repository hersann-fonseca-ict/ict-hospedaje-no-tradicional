import { HttpClient, HttpHeaders } from "@angular/common/http";
import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild
} from "@angular/core";
import { Router } from "@angular/router";
import { DataTableDirective } from "angular-datatables";
import { CommonService } from "app/services/common/common.service";
import { EmpresaService } from "app/services/Empresa/empresa.service";
import { LocalizacionInmuebleService } from "app/services/LocalizacionInmueble/localizacion-inmueble.service";
import { Empresa } from "app/services/Modelos/empresa";
import { LocalizacionInmueble } from "app/services/Modelos/localizacion-inmueble";
import { OAuthHelperService } from "app/services/OAuthHerlper/oauth-helper.service";
import { Perfil } from "app/services/Perfiles/perfil";
import { PerfilExcel } from "app/services/Perfiles/perfil-excel";
import { PerfilModel } from "app/services/Perfiles/perfil-model";
import { PerfilesService } from "app/services/Perfiles/perfiles.service";
import { TipoServicio } from "app/services/TipoServicio/tipo-servicio";
import { TipoServicioService } from "app/services/TipoServicio/tipo-servicio.service";
import { DataTablesResponse } from "app/table-list/datatableRespose";
import { LenguajeTabla } from "app/table-list/lenguajeTabla";
import { environment } from "environments/environment";
import { Subject } from "rxjs";
import { PerfilView } from "./../services/Perfiles/perfil-view";

@Component({
  selector: "app-perfiles",
  templateUrl: "./perfiles.component.html",
  styleUrls: ["./perfiles.component.css"],
})
export class PerfilesComponent implements AfterViewInit, OnDestroy, OnInit {
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  listaPerfiles: PerfilView[];
  lista: PerfilModel[];
  verDetalles: boolean = false;
  detallesPerfil: Perfil = new Perfil();
  nombreTipoServicio: string = "";
  public mascara = [
    /\d/,
    "-",
    /\d/,
    /\d/,
    /\d/,
    /\d/,
    "-",
    /\d/,
    /\d/,
    /\d/,
    /\d/,
  ];
  mask: boolean = false;
  public cedulaIdentidadMaskara = [
    /\d/,
    "-",
    /\d/,
    /\d/,
    /\d/,
    /\d/,
    "-",
    /\d/,
    /\d/,
    /\d/,
    /\d/,
  ];

  fileName = "Perfiles.xlsx";

  perfilActualizar: Perfil = new Perfil();
  inmuebleActualizar: LocalizacionInmueble = new LocalizacionInmueble();
  opcionSeleccionada: PerfilView = new PerfilView();
  empresaActualizar: Empresa = new Empresa();
  estadoAccion: boolean;
  inmuebleEditable: boolean;

  listadoExcel: PerfilExcel[];
  perfilModel: PerfilView;
  motivoRechazo: string = "";
  motivoDesafiliacion: string = "";
  tituloModalConfirmacion: string = "";
  mensajeModalConfirmacion: string = "";
  tituloModalRechazo: string = "";
  mensajeModalRechazo: string = "";
  cuerpoCorreo: string = "";
  constructor(
    private common: CommonService,
    private tiposervicioService: TipoServicioService,
    private perfilService: PerfilesService,
    private http: HttpClient,
    private oauth: OAuthHelperService,
    private router: Router,
    private localizacionService: LocalizacionInmuebleService,
    private empresaService: EmpresaService
  ) { }

  ngOnInit() {
    this.common._setLoading(true);
    let headers = new HttpHeaders({
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
    var cabecera: Object = new Object();
    cabecera["headers"] = headers;
    let isAnalista =
      this.oauth.obtenerRoles().find((e) => e.toLowerCase() === "analista") !==
      undefined;
    let analista = isAnalista ? this.oauth.token : undefined;
    let service = `obtenerEstablecimentosPorUsuario?idUsuario=${analista ? analista.id : ""
      }`;
    let urlService = `${environment.servicioPerfiles}/${service}`;
    this.dtOptions = {
      pagingType: "full_numbers",
      pageLength: 5,
      autoWidth: true,
      responsive: true,
      lengthMenu: [5, 25, 50, 100],
      processing: true,
      serverSide: true,
      language: LenguajeTabla.spanish_datatables,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
          .post<DataTablesResponse>(urlService, dataTablesParameters, cabecera)
          .subscribe(
            (resp) => {
              if (resp == null) {
                this.listaPerfiles = [];
              } else {
                this.listaPerfiles = resp.data;
              }
              this.common._setLoading(false);
              callback({
                recordsTotal: resp.recordsTotal,
                recordsFiltered: resp.recordsFiltered,
                data: [],
              });
            },
            (error) => {
              this.common._setLoading(false);
              alert(
                "Ocurrio un error con los servicios. Contacte con el administrador"
              );
            }
          );
      },
      columns: [
        { data: "tipoServicio" },
        { data: "tipoIdentificacion" },
        { data: "identificacion" },
        { data: "nombre" },
        { data: "codigo" },
        { data: "provincia" },
        { data: "canton" },
        { data: "distrito" },
        { data: "direccion" },
        { data: "correo" },
        { data: "telefono" },
        { data: "url" },
        { data: "fechaAprobacion" },
        { data: "fechaDesafiliacion" },
        { data: "fechaRechazo" },
      ],
    };
  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

  recargar(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      // Destroy the table first
      dtInstance.destroy();
      // Call the dtTrigger to rerender again
      this.dtTrigger.next();
    });
  }

  verDetallesPerfil(perfil: PerfilView) {
    this.common._setLoading(true);
    this.verDetalles = true;
    this.perfilModel = perfil;
    this.opcionSeleccionada = perfil;
    this.perfilService
      .obtenerPerfilPorId(perfil.perfil)
      .then((respuesta: Perfil) => {
        //se encuentra el perfil a actualizar
        if (respuesta) {
          this.detallesPerfil = respuesta;
          this.common._setLoading(false);
          //this.obtenerNombreTipoServicio(this.detallesPerfil.tipoServicio);
        }
      })
      .catch((error) => {
        //fallo al buscar el perfil para actualizar
        this.common._setLoading(false);
        alert(
          "Ocurrio un error con los servicios. Contacte con el administrador"
        );
      });
  }

  aprobar(perfil: PerfilView) {
    if (perfil.perfilNombre == "HNT") {
      this.opcionSeleccionada = perfil;
      this.tituloModalConfirmacion = "Aprobar inmueble";
      this.mensajeModalConfirmacion =
        "¿Está seguro que desea aprobar el inmueble?";
    } else if (perfil.perfilNombre == "EI") {
      this.opcionSeleccionada = perfil;
      this.tituloModalConfirmacion = "Aprobar empresa";
      this.mensajeModalConfirmacion =
        "¿Está seguro que desea aprobar la empresa?";
    }
  }

  desafiliar(perfil: PerfilView) {
    if (perfil.perfilNombre == "HNT") {
      this.opcionSeleccionada = perfil;
      this.tituloModalConfirmacion = "Desafiliar inmueble";
      this.mensajeModalConfirmacion =
        "¿Está seguro que desea desafiliar el inmueble?";
    } else if (perfil.perfilNombre == "EI") {
      this.opcionSeleccionada = perfil;
      this.tituloModalConfirmacion = "Desafiliar empresa";
      this.mensajeModalConfirmacion =
        "¿Está seguro que desea desafiliar la empresa?";
    }
  }

  rechazar(perfil: PerfilView) {
    if (perfil.perfilNombre == "HNT") {
      this.opcionSeleccionada = perfil;
      this.tituloModalConfirmacion = "Rechazar inmueble";
      this.mensajeModalConfirmacion =
        "¿Está seguro que desea rechazar el inmueble?";
      this.tituloModalRechazo = "Rechazar inmueble";
    } else if (perfil.perfilNombre == "EI") {
      this.opcionSeleccionada = perfil;
      this.tituloModalConfirmacion = "Rechazar empresa";
      this.mensajeModalConfirmacion =
        "¿Está seguro que desea rechazar la empresa?";
      this.tituloModalRechazo = "Rechazar empresa";
    }
  }

  loadEstadosInmueble(inmueble: LocalizacionInmueble, estado: 'aprobar' | 'rechazar' | 'pendienteDesafiliar' | 'desafiliar') {
    if (estado != 'pendienteDesafiliar') {
      inmueble.aprobar = false;
      inmueble.rechazar = false;
      inmueble.pendienteDesafiliar = false;
      inmueble.desafiliar = false;
      inmueble.motivoRechazo = null;
      if (estado != 'desafiliar') {
        inmueble.motivoDesafiliacion = null;
      }
      inmueble.fechaAprobacion = null;
      inmueble.fechaRechazo = null;
      inmueble.fechaDesafiliacion = null;
    }
    switch (estado) {
      case 'aprobar':
        inmueble.aprobar = true;
        inmueble.fechaAprobacion = new Date();
        break;
      case 'rechazar':
        inmueble.rechazar = true;
        inmueble.fechaRechazo = new Date();
        inmueble.motivoRechazo = this.motivoRechazoValue;
        break;
      case 'desafiliar':
        inmueble.desafiliar = true;
        inmueble.fechaDesafiliacion = new Date();
        break;
      default:
        inmueble.pendienteDesafiliar = true;
        inmueble.motivoDesafiliacion = this.motivoDesafiliacion;
        break;
    }
    return inmueble;
  }

  loadEstadosEmpresa(empresa: Empresa, estado: 'aprobar' | 'rechazar' | 'pendienteDesafiliar' | 'desafiliar') {
    if (estado != 'pendienteDesafiliar') {
      empresa.aprobar = false;
      empresa.rechazar = false;
      empresa.pendienteDesafiliar = false;
      empresa.desafiliar = false;
      empresa.motivoRechazo = null;
      if (estado != 'desafiliar') {
        empresa.motivoDesafiliacion = null;
      }
      empresa.fechaAprobacion = null;
      empresa.fechaRechazo = null;
      empresa.fechaDesafiliacion = null;
    }
    switch (estado) {
      case 'aprobar':
        empresa.aprobar = true;
        empresa.fechaAprobacion = new Date();
        break;
      case 'rechazar':
        empresa.rechazar = true;
        empresa.fechaRechazo = new Date();
        empresa.motivoRechazo = this.motivoRechazoValue;
        break;
      case 'desafiliar':
        empresa.desafiliar = true;
        empresa.fechaDesafiliacion = new Date();
        break;
      default:
        empresa.pendienteDesafiliar = true;
        empresa.motivoDesafiliacion = this.motivoDesafiliacion;
        break;
    }
    return empresa;
  }

  aprobarPerfil() {
    this.common._setLoading(true);
    if (this.opcionSeleccionada.perfilNombre == "HNT") {
      //Actualizar inmueble
      this.localizacionService
        .obtenerInmublePorId(this.opcionSeleccionada.inmueble)
        .then((inmueble: LocalizacionInmueble) => {
          if (inmueble) {
            this.inmuebleActualizar = this.loadEstadosInmueble(inmueble, 'aprobar');
            this.localizacionService
              .actualizarInmueble(this.inmuebleActualizar)
              .then((respuesta: LocalizacionInmueble) => {
                if (respuesta) {
                  this.cuerpoCorreo = `El inmueble con el nombre <b>${respuesta.alojamiento.nombre}</b> con el código <b>${respuesta.codigo}</b> ha sido aprobado exitosamente.`;
                  this.localizacionService
                    .enviarCorreo(
                      this.inmuebleActualizar,
                      "Aprobación de inmueble",
                      "aprobar",
                      this.cuerpoCorreo
                    )
                    .then((respuesta) => {
                      if (respuesta) {
                        alert(
                          "Se aprobó exitosamente y se envio correo de notificación"
                        );
                        this.recargar();
                        this.common._setLoading(false);
                      } else {
                        alert("Se aprobó exitosamente");
                        this.recargar();
                        this.common._setLoading(false);
                      }
                    })
                    .catch((error) => {
                      alert(
                        "Se aprobó exitosamente, pero ocurrió un problema al enviar el correo de notificación"
                      );
                      this.recargar();
                      this.common._setLoading(false);
                    });
                } else {
                  alert("Ocurrio un error al aprobar el inmueble");
                  this.recargar();
                  this.common._setLoading(false);
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrio un error al aprobar el inmueble");
                this.recargar();
                this.common._setLoading(false);
              });
          }
        })
        .catch((error) => {
          //fallo al buscar el perfil para actualizar
          alert(
            "Ocurrio un error con los servicios. Contacte con el administrador"
          );
          this.recargar();
          this.common._setLoading(false);
        });
    } else if (this.opcionSeleccionada.perfilNombre == "EI") {
      //Actualizar empresa
      this.empresaService
        .obtenerEmpresaPorId(this.opcionSeleccionada.empresa)
        .then((empresa: Empresa) => {
          if (empresa) {
            this.empresaActualizar = this.loadEstadosEmpresa(empresa, 'aprobar');
            this.empresaService
              .actualizarEmpresa(this.empresaActualizar)
              .then((respuesta: Empresa) => {
                if (respuesta) {
                  this.cuerpoCorreo = `El inmueble con el representante <b>${respuesta.nombreRepresentante} ${respuesta.primerApe} ${respuesta.segundoApe}</b> con la razón social <b>${respuesta.razonSocial}</b> ha sido aprobado exitosamente.`;
                  this.empresaService
                    .enviarCorreoEmpresa(
                      this.empresaActualizar,
                      "Aprobación de inmueble",
                      "aprobar",
                      this.cuerpoCorreo
                    )
                    .then((respuesta) => {
                      if (respuesta) {
                        alert(
                          "Se aprobó exitosamente y se envio correo de notificación"
                        );
                        this.recargar();
                        this.common._setLoading(false);
                      } else {
                        alert("Se aprobó exitosamente");
                        this.recargar();
                        this.common._setLoading(false);
                      }
                    })
                    .catch((error) => {
                      alert(
                        "Se aprobó exitosamente, pero ocurrió un problema al enviar el correo de notificación"
                      );
                      this.recargar();
                      this.common._setLoading(false);
                    });
                } else {
                  alert("Ocurrio un error al aprobar la empresa");
                  this.recargar();
                  this.common._setLoading(false);
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrio un error al aprobar la empresa");
                this.recargar();
                this.common._setLoading(false);
              });
          }
        })
        .catch((error) => {
          //fallo al buscar el perfil para actualizar
          alert(
            "Ocurrio un error con los servicios. Contacte con el administrador"
          );
          this.recargar();
          this.common._setLoading(false);
        });
    }
  }

  decodeString(string: string) {
    return decodeURIComponent(escape(string));
  }

  nombreCompletoUsuario(): string {
    let token: any = this.oauth.token;
    let user = { nombre: "", primerApellido: "", segundoApellido: "" };
    if (token.tipoServicio === "EI") {
      user.nombre = token.nombreRepresentante;
      user.primerApellido = token.primerApellido;
      user.segundoApellido = token.segundoApellido;
    } else if (token.tipoServicio === "HNT") {
      if (this.decodeString(token.tipoPersona) === "Persona Física") {
        user.nombre = token.nombre;
        user.primerApellido = token.apellido1;
        user.segundoApellido = token.apellido2;
      } else {
        user.nombre = token.nombreRepresentante;
        user.primerApellido = token.apellido1Representante;
        user.segundoApellido = token.apellido2Representante;
      }
    }
    return user.nombre && user.primerApellido && user.segundoApellido ?
      this.decodeString(
        `${user.nombre} ${user.primerApellido} ${user.segundoApellido}`
      ) : token.user_name;
  }

  async enviarCorreoDesafiliacion(
    isEmpresa: boolean,
    representante: string,
    detalle: string
  ) {
    let tipoEstablecimiento = isEmpresa ? "de la empresa" : "del inmueble";
    let asunto = `Desafiliación de ${isEmpresa ? "una Empresa" : "un Inmueble"
      }`;
    let cuerpo =
      `El analista <b>${this.nombreCompletoUsuario()}</b>` +
      `, solicitó la desafiliación ${tipoEstablecimiento} con el representante` +
      `, ${representante}${isEmpresa
        ? " y la razón social <b>" + detalle + "</b>"
        : " y el nombre " + detalle + ""
      }` +
      `, por el siguiente motivo, <i>"${this.motivoDesafiliacion}"</i>.`;
    await this.perfilService
      .enviarCorreoUsuario(this.opcionSeleccionada.jefe, asunto, cuerpo)
      .then((respuesta) => {
        alert(`${respuesta.estado} de notificación al jefe`);
      })
      .catch((error) => {
        alert(
          "Se asignó éxitosamente, pero ocurrió un problema al enviar el correo de notificación"
        );
      });
  }

  desafiliarPerfil() {
    this.common._setLoading(true);
    if (this.opcionSeleccionada.perfilNombre == "HNT") {
      //Actualizar inmueble
      this.localizacionService
        .obtenerInmublePorId(this.opcionSeleccionada.inmueble)
        .then(async (inmueble: LocalizacionInmueble) => {
          if (inmueble) {
            this.inmuebleActualizar = this.loadEstadosInmueble(inmueble, 'pendienteDesafiliar');
            await this.localizacionService
              .actualizarInmueble(this.inmuebleActualizar)
              .then(async (respuesta: LocalizacionInmueble) => {
                if (respuesta) {
                  let nomRe: string;
                  if (inmueble.perfil.personaFisica) {
                    let persona = inmueble.perfil.personaFisica;
                    nomRe = `${persona.nombre} ${persona.apellido1} ${persona.apellido2}`;
                  } else {
                    let persona = inmueble.perfil.personaJuridica;
                    nomRe = `${persona.nombreRepresentante} ${persona.apellido1Representante} ${persona.apellido2Representante}`;
                  }
                  await this.enviarCorreoDesafiliacion(
                    false,
                    nomRe,
                    `<b>${inmueble.alojamiento.nombre}</b>, código <b>${inmueble.codigo}</b>`
                  );
                } else {
                  alert("Ocurrio un error al desafiliar el inmueble");
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrio un error al desafiliar el inmueble");
              });
          }
        })
        .catch((error) => {
          //fallo al buscar el perfil para actualizar
          alert(
            "Ocurrio un error con los servicios. Contacte con el administrador"
          );
        }).finally(() => {
          this.regresarTabla();
          this.common._setLoading(false);
        });
    } else if (this.opcionSeleccionada.perfilNombre == "EI") {
      //Actualizar empresa
      this.cuerpoCorreo =
        "Su empresa ha sido desafiliado.-Por favor comuníquese con la sucursal del ICT más cercana.";
      this.empresaService
        .obtenerEmpresaPorId(this.opcionSeleccionada.empresa)
        .then(async (empresa: Empresa) => {
          if (empresa) {
            this.empresaActualizar = this.loadEstadosEmpresa(empresa, 'pendienteDesafiliar');
            await this.empresaService
              .actualizarEmpresa(this.empresaActualizar)
              .then(async (respuesta: Empresa) => {
                if (respuesta) {
                  await this.enviarCorreoDesafiliacion(
                    true,
                    `${empresa.nombreRepresentante} ${empresa.primerApe} ${empresa.segundoApe}`,
                    empresa.razonSocial
                  );
                } else {
                  alert(
                    "Ocurrio un error al enviar la solicitud de desafiliación de la empresa"
                  );
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrio un error al desafiliar la empresa");
              });
          }
        })
        .catch((error) => {
          //fallo al buscar el perfil para actualizar
          alert(
            "Ocurrio un error con los servicios. Contacte con el administrador"
          );
        }).finally(() => {
          this.regresarTabla();
          this.common._setLoading(false);
        });;
    }
  }

  rechazarPerfil() {
    this.common._setLoading(true);
    if (this.opcionSeleccionada.perfilNombre == "HNT") {
      //Actualizar inmueble
      this.localizacionService
        .obtenerInmublePorId(this.opcionSeleccionada.inmueble)
        .then((inmueble: LocalizacionInmueble) => {
          if (inmueble) {
            this.inmuebleActualizar = this.loadEstadosInmueble(inmueble, 'rechazar');
            this.localizacionService
              .actualizarInmueble(this.inmuebleActualizar)
              .then((respuesta: LocalizacionInmueble) => {
                if (respuesta) {
                  this.limpiarVariables();
                  this.cuerpoCorreo = `-El inmueble con el nombre <b>${respuesta.alojamiento.nombre}</b> ` +
                    `con el código <b>${respuesta.codigo}</b> ha sido rechazado por el siguiente motivo, <i>"${respuesta.motivoRechazo}"</i>` +
                    '-Por favor comuníquese con la sucursal del ICT más cercana.';
                  this.localizacionService
                    .enviarCorreo(
                      this.inmuebleActualizar,
                      "Rechazo de inmueble",
                      "rechazar",
                      this.cuerpoCorreo
                    )
                    .then((respuesta) => {
                      if (respuesta) {
                        alert(
                          "Se rechazó exitosamente y se envio correo de notificación"
                        );
                        this.recargar();
                        this.common._setLoading(false);
                      } else {
                        alert("Se rechazó exitosamente");
                        this.recargar();
                        this.common._setLoading(false);
                      }
                    })
                    .catch((error) => {
                      alert(
                        "Se rechazó exitosamente, pero ocurrió un problema al enviar el correo de notificación"
                      );
                      this.recargar();
                      this.common._setLoading(false);
                      this.limpiarVariables();
                    });
                } else {
                  alert("Ocurrió un error al rechazar el inmueble");
                  this.recargar();
                  this.common._setLoading(false);
                  this.limpiarVariables();
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrió un error al rechazar el inmueble");
                this.recargar();
                this.common._setLoading(false);
                this.limpiarVariables();
              });
          }
        })
        .catch((error) => {
          //fallo al buscar el perfil para actualizar
          alert(
            "Ocurrió un error con los servicios. Contacte con el administrador"
          );
          this.recargar();
          this.common._setLoading(false);
          this.limpiarVariables();
        });
    } else if (this.opcionSeleccionada.perfilNombre == "EI") {
      this.empresaService
        .obtenerEmpresaPorId(this.opcionSeleccionada.empresa)
        .then((empresa: Empresa) => {
          if (empresa) {
            this.empresaActualizar = this.loadEstadosEmpresa(empresa, 'rechazar');
            this.empresaService
              .actualizarEmpresa(this.empresaActualizar)
              .then((respuesta: Empresa) => {
                if (respuesta) {
                  this.limpiarVariables();
                  this.cuerpoCorreo = `-La empresa con el representante <b>${respuesta.nombreRepresentante} ${respuesta.primerApe} ${respuesta.segundoApe}</b> ` +
                    `con la razón social <b>${respuesta.razonSocial}</b> ha sido rechazada por el siguiente motivo, <i>"${respuesta.motivoRechazo}"</i>` +
                    '-Por favor comuníquese con la sucursal del ICT más cercana.';
                  this.empresaService
                    .enviarCorreoEmpresa(
                      this.empresaActualizar,
                      "Rechazo de empresa",
                      "rechazar",
                      this.cuerpoCorreo
                    )
                    .then((respuesta) => {
                      if (respuesta) {
                        alert(
                          "Se rechazó exitosamente y se envió correo de notificación"
                        );
                        this.recargar();
                        this.common._setLoading(false);
                      } else {
                        alert("Se rechazó exitosamente");
                        this.recargar();
                        this.common._setLoading(false);
                      }
                    })
                    .catch((error) => {
                      alert(
                        "Se rechazó exitosamente, pero ocurrió un problema al enviar el correo de notificación"
                      );
                      this.recargar();
                      this.common._setLoading(false);
                      this.limpiarVariables();
                    });
                } else {
                  alert("Ocurrió un error al rechazar la empresa");
                  this.recargar();
                  this.common._setLoading(false);
                  this.limpiarVariables();
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrió un error al rechazar la empresa");
                this.recargar();
                this.common._setLoading(false);
                this.limpiarVariables();
              });
          }
        })
        .catch((error) => {
          //fallo al buscar el perfil para actualizar
          alert(
            "Ocurrió un error con los servicios. Contacte con el administrador"
          );
          this.recargar();
          this.common._setLoading(false);
        });
    }
  }

  async enviarCorreoActualizacion(
    codigo: string,
    nombre: string,
    correo: string
  ) {
    let asunto = 'Solicitud de Actualización de un Inmueble';
    let cuerpo =
      `Se aprobó la solicitud para la actualización del Inmueble con el nombre ${nombre}` +
      ` y el código <b>${codigo}</b>.`;
    await this.perfilService
      .enviarCorreoUsuario(undefined, asunto, cuerpo, correo)
      .then((respuesta) => {
        alert(`Se actualizó el estado del inmueble y ${respuesta.estado.toLowerCase()} de notificación al usuario`);
      })
      .catch((error) => {
        alert(
          "Se actualizó éxitosamente, pero ocurrió un problema al enviar el correo de notificación"
        );
      });
  }

  actualizarPerfil() {
    this.common._setLoading(true);
    this.localizacionService
      .obtenerInmublePorId(this.perfilModel.inmueble)
      .then(async (inmueble: LocalizacionInmueble) => {
        if (inmueble) {
          inmueble.editable = this.perfilModel.editable;
          this.inmuebleActualizar = inmueble;
          await this.localizacionService
            .actualizarInmueble(this.inmuebleActualizar)
            .then(async (respuesta: LocalizacionInmueble) => {
              if (respuesta) {
                if (respuesta.editable) {
                  await this.enviarCorreoActualizacion(
                    inmueble.codigo,
                    inmueble.alojamiento.nombre,
                    inmueble.correo
                  );
                } else {
                  alert("Se actualizó éxitosamente el estado del inmueble");
                }
              } else {
                alert("Ocurrio un error al actualizar estado del inmueble");
              }
            })
            .catch((error) => {
              //fallo
              alert("Ocurrio un error al actualizar el estado del inmueble");
            })
        }
      })
      .catch((error) => {
        alert(
          "Ocurrio un error con los servicios. Contacte con el administrador"
        );
      }).finally(() => {
        this.regresarTabla();
        this.common._setLoading(false);
      });
  }

  regresarTabla() {
    this.verDetalles = false;
    this.motivoDesafiliacion = "";
    this.motivoRechazo = "";
    this.recargar();
  }

  obtenerNombreTipoServicio(id: number) {
    this.common._setLoading(true);
    this.tiposervicioService
      .obtenerPorId(id)
      .then((respuesta: TipoServicio) => {
        this.nombreTipoServicio = respuesta.nombre;
        this.common._setLoading(false);
      })
      .catch((error) => {
        this.common._setLoading(false);
        alert(
          "Ocurrio un error con los servicios. Contacte con el administrador"
        );
      });
  }

  setTipoFisica(tipoIdentificacion) {
    if (tipoIdentificacion == "Cédula Identidad") {
      this.mask = true;
      this.mascara = this.cedulaIdentidadMaskara;
    } else {
      this.mask = false;
    }
  }

  get motivoRechazoValue() {
    return this.motivoRechazo;
  }

  limpiarVariables() {
    this.opcionSeleccionada = null;
    this.motivoRechazo = "";
    this.motivoDesafiliacion = "";
    this.cuerpoCorreo = "";
  }

  mostrarOpcionAprobar(perfiles: PerfilView) {
    return !perfiles.aprobado || (!perfiles.aprobado && perfiles.rechazado);
  }

  mostrarOpcionDesafiliar(perfiles: PerfilView) {
    return !perfiles.pendienteDesafiliar && !perfiles.desafiliado;
  }

  mostrarOpcionRechazar(perfiles: PerfilView) {
    return !perfiles.aprobado && !perfiles.rechazado;
  }

  editableButton() {
    this.perfilModel.editable = this.opcionSeleccionada.editable;
  }

  noUsarGuion(e: any): boolean {
    return e.keyCode != 189;
  }
}
