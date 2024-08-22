import { HttpClient, HttpHeaders } from "@angular/common/http";
import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild
} from "@angular/core";
import { DataTableDirective } from "angular-datatables";
import { CommonService } from "app/services/common/common.service";
import { EmpresaService } from "app/services/Empresa/empresa.service";
import { LocalizacionInmuebleService } from "app/services/LocalizacionInmueble/localizacion-inmueble.service";
import { Empresa } from "app/services/Modelos/empresa";
import { LocalizacionInmueble } from "app/services/Modelos/localizacion-inmueble";
import { OAuthHelperService } from "app/services/OAuthHerlper/oauth-helper.service";
import { Perfil } from "app/services/Perfiles/perfil";
import { PerfilModel } from "app/services/Perfiles/perfil-model";
import { PerfilesService } from "app/services/Perfiles/perfiles.service";
import { DataTablesResponse } from "app/table-list/datatableRespose";
import { LenguajeTabla } from "app/table-list/lenguajeTabla";
import { environment } from "environments/environment";
import { Subject } from "rxjs";
import { PerfilView } from "../services/Perfiles/perfil-view";

@Component({
  selector: "app-perfiles",
  templateUrl: "./clientesPendientesDeDesafiliar.component.html",
  styleUrls: ["./clientesPendientesDeDesafiliar.component.css"],
})
export class ClientesPendientesDeDesafiliarComponent implements AfterViewInit, OnDestroy, OnInit {
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  listaPerfiles: PerfilView[];
  lista: PerfilModel[];
  verDetalles: boolean = false;
  detallesPerfil: Perfil = new Perfil();
  inmuebleActualizar: LocalizacionInmueble = new LocalizacionInmueble();
  opcionSeleccionada: PerfilView = new PerfilView();
  empresaActualizar: Empresa = new Empresa();
  perfilModel: PerfilView;
  tituloModalConfirmacion: string = "";
  mensajeModalConfirmacion: string = "";

  constructor(
    private common: CommonService,
    private perfilService: PerfilesService,
    private http: HttpClient,
    private oauth: OAuthHelperService,
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
    let service = "obtenerEstablecimentosPendientesDesafiliar";
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
        { data: "motivoDesafiliacion" }
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
    this.perfilService
      .obtenerPerfilPorId(perfil.perfil)
      .then((respuesta: Perfil) => {
        //se encuentra el perfil a actualizar
        if (respuesta) {
          this.detallesPerfil = respuesta;
          this.common._setLoading(false);
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

  enviarCorreoDesafiliacion(
    emailDestinatario: string,
    isEmpresa: boolean,
    representante: string,
    detalle: string
  ) {
    let tipoEstablecimiento = isEmpresa ? "la empresa" : "el inmueble";
    let asunto = `Desafiliación de ${isEmpresa ? "una Empresa" : "un Inmueble"
      }`;
    let cuerpo =
      `Se ha desafiliado ${tipoEstablecimiento} con el representante <b>${representante}</b>
      ${isEmpresa
        ? " y la razón social " + detalle
        : " y el nombre <b>" + detalle + "</b>"
      }`;
    this.perfilService.enviarCorreoUsuarioPerfil(emailDestinatario, asunto, cuerpo)
      .then((respuesta) => {
        alert(`${respuesta.estado} de notificación al usuario que registró la propiedad en la página Acceso Público`);
        this.common._setLoading(false);
        this.recargar();
      })
      .catch((error) => {
        alert(
          "Se desafilió éxitosamente, pero ocurrió un problema al enviar el correo de notificación"
        );
        this.recargar();
        this.common._setLoading(false);
      });

  }

  loadEstadosInmueble(inmueble: LocalizacionInmueble) {
    inmueble.aprobar = false;
    inmueble.rechazar = false;
    inmueble.pendienteDesafiliar = false;
    inmueble.desafiliar = false;
    inmueble.motivoRechazo = null;
    inmueble.fechaAprobacion = null;
    inmueble.fechaRechazo = null;
    inmueble.fechaDesafiliacion = null;
    inmueble.desafiliar = true;
    inmueble.fechaDesafiliacion = new Date();
    return inmueble;
  }

  loadEstadosEmpresa(empresa: Empresa) {
    empresa.aprobar = false;
    empresa.rechazar = false;
    empresa.pendienteDesafiliar = false;
    empresa.desafiliar = false;
    empresa.motivoRechazo = null;
    empresa.fechaAprobacion = null;
    empresa.fechaRechazo = null;
    empresa.fechaDesafiliacion = null;
    empresa.desafiliar = true;
    empresa.fechaDesafiliacion = new Date();
    return empresa;
  }

  desafiliarPerfil() {
    this.common._setLoading(true);
    if (this.opcionSeleccionada.perfilNombre == "HNT") {
      //Actualizar inmueble
      this.localizacionService.obtenerInmublePorId(this.opcionSeleccionada.inmueble)
        .then((inmueble: LocalizacionInmueble) => {
          if (inmueble) {
            this.limpiarVariables();
            this.inmuebleActualizar = this.loadEstadosInmueble(inmueble);
            this.localizacionService.actualizarInmueble(this.inmuebleActualizar)
              .then((respuesta: LocalizacionInmueble) => {
                if (respuesta) {
                  let nomRe: string;
                  if (inmueble.perfil.personaFisica) {
                    let persona = inmueble.perfil.personaFisica;
                    nomRe = `${persona.nombre} ${persona.apellido1} ${persona.apellido2}`;
                  } else {
                    let persona = inmueble.perfil.personaJuridica;
                    nomRe = `${persona.nombreRepresentante} ${persona.apellido1Representante} ${persona.apellido2Representante}`;
                  }
                  this.enviarCorreoDesafiliacion(
                    respuesta.perfil.correo,
                    false,
                    nomRe,
                    `<b>${inmueble.alojamiento.nombre}</b> con el código <b>${inmueble.codigo}</b>`
                  );
                } else {
                  alert("Ocurrió un error al desafiliar el inmueble");
                  this.recargar();
                  this.common._setLoading(false);
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrio un error al desafiliar el inmueble");
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
          this.limpiarVariables();
        });
    } else if (this.opcionSeleccionada.perfilNombre == "EI") {
      //Actualizar empresa
      this.empresaService.obtenerEmpresaPorId(this.opcionSeleccionada.empresa)
        .then((empresa: Empresa) => {
          this.limpiarVariables();
          if (empresa) {
            this.empresaActualizar = this.loadEstadosEmpresa(empresa);
            this.empresaService.actualizarEmpresa(this.empresaActualizar)
              .then((respuesta: Empresa) => {
                if (respuesta) {
                  this.enviarCorreoDesafiliacion(
                    respuesta.correo,
                    true,
                    `${empresa.nombreRepresentante} ${empresa.primerApe} ${empresa.segundoApe}`,
                    empresa.razonSocial
                  );

                } else {
                  alert(
                    "Ocurrio un error al enviar la solicitud de desafiliación de la empresa"
                  );
                  this.recargar();
                  this.common._setLoading(false);
                }
              })
              .catch((error) => {
                //fallo
                alert("Ocurrio un error al desafiliar la empresa");
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
          this.limpiarVariables();
        });
    }
  }

  regresarTabla() {
    this.verDetalles = false;
    //window.location.reload();
    this.recargar();
  }

  limpiarVariables() {
    this.opcionSeleccionada = null;
  }

  mostrarOpcionDesafiliar(perfiles: PerfilView) {
    return !perfiles.pendienteDesafiliar || !perfiles.desafiliado;
  }
}
