import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MsalService } from '@azure/msal-angular';
import { DataTableDirective } from 'angular-datatables';
import { CommonService } from 'app/services/common/common.service';
import { OAuthHelperService } from 'app/services/OAuthHerlper/oauth-helper.service';
import { EstablecimientoPorAnalista } from 'app/services/Perfiles/establecimientoPorAnalista';
import { Perfil } from 'app/services/Perfiles/perfil';
import { PerfilView } from 'app/services/Perfiles/perfil-view';
import { PerfilesService } from 'app/services/Perfiles/perfiles.service';
import { UsuarioSistema } from 'app/services/Usuarios/usuarios';
import { DataTablesResponse } from 'app/table-list/datatableRespose';
import { LenguajeTabla } from 'app/table-list/lenguajeTabla';
import { environment } from 'environments/environment';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-perfiles',
  templateUrl: './asignarClientePorAnalista.component.html',
  styleUrls: ['./asignarClientePorAnalista.component.css']
})
export class AsignarClientePorAnalistaComponent implements AfterViewInit, OnDestroy, OnInit {
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  disabledAsignar: boolean = true;

  idJefe: number = 1;
  listaPerfiles: PerfilView[];
  listaUsuarios: UsuarioSistema[];
  idUsuarioSeleccionado: number = 0;
  idSeleccion: number = 0;
  verDetalles: boolean = false;
  detallesPerfil: Perfil = new Perfil();
  opcionSeleccionada: PerfilView = new PerfilView();
  perfilModel: PerfilView;
  tituloModalConfirmacion: string = "";
  mensajeModalConfirmacion: string = "";
  constructor(private common: CommonService, private perfilService: PerfilesService, private http: HttpClient,
    private oauth: OAuthHelperService, private authService: MsalService) {
  }

  ngOnInit() {
    this.common._setLoading(true);
    let headers = new HttpHeaders({
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
    var cabecera: Object = new Object();
    cabecera['headers'] = headers;
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      autoWidth: true,
      responsive: true,
      lengthMenu: [5, 25, 50, 100],
      processing: true,
      serverSide: true,
      language: LenguajeTabla.spanish_datatables,
      ajax: (dataTablesParameters: any, callback) => {
        this.http.post<DataTablesResponse>(
          environment.servicioPerfiles + "/obtenerEstablecimentosPendientesAprobar", dataTablesParameters, cabecera
        ).subscribe(resp => {
          if (resp == null) {
            this.listaPerfiles = [];
          } else {
            this.listaPerfiles = resp.data;
          }
          this.common._setLoading(false);
          callback({
            recordsTotal: resp.recordsTotal,
            recordsFiltered: resp.recordsFiltered,
            data: []
          });
        }, error => {
          this.common._setLoading(false);
          alert('Ocurrio un error con los servicios. Contacte con el administrador')
        });

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
        { data: "url" }
      ]
    };

    this.obtenerAnalistas(cabecera);

    this.obtieneIdJefe(cabecera);

  }

  private obtenerAnalistas(cabecera: Object) {
    let numeroRolAnalista = 22;
    this.http.get<UsuarioSistema[]>(environment.servicioUsuarios + "/obtienerUsuariosPorRolId?rolId=" + numeroRolAnalista, cabecera
    ).subscribe(resp => {
      if (resp == null) {
        this.listaUsuarios = [];
      } else {
        this.listaUsuarios = resp;
      }
    });
  }

  private obtieneIdJefe(cabecera: Object) {
    this.http.get<number>(environment.servicioUsuarios + "/obtenerJefeIdPorCorreo?correoJefe="
      + this.authService.instance.getActiveAccount().username, cabecera
    ).subscribe(resp => {
      if (resp == null) {
        this.idJefe = 1;
      } else {
        this.idJefe = resp;
      }
    });
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

  habilitarAsignar(){
    this.disabledAsignar=false;
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

  asignar(perfil: PerfilView) {
    this.opcionSeleccionada = perfil;
    this.tituloModalConfirmacion = "Asignar analista";
    this.mensajeModalConfirmacion = "Seleccione un usuario para que sea asignado al inmueble";
  }

  quitarAsignacion(perfil: PerfilView) {
    this.opcionSeleccionada = perfil;
    this.tituloModalConfirmacion = "Quitar asignación de analista";
    this.mensajeModalConfirmacion = "Seleccione un usuario para que sea asignado al inmueble";
    this.mensajeModalConfirmacion = "¿Está seguro que desea quitar la asignación del analsita?";
  }

  asignarInmuebleAAnalista() {
    this.common._setLoading(true);
    if (this.opcionSeleccionada.perfilNombre == "HNT") {
      this.perfilService.asignarEstablecimentoAAnalista(this.idSeleccion, 0, this.opcionSeleccionada.inmueble, this.idJefe)
        .then((res: EstablecimientoPorAnalista) => {
          if (res != null) {
            this.enviarCorreo(
              res.jefe.nombreUsuario,
              res.usuarioSistema.id,
              "Asignación",
              false,
              `el nombre <b>${res.localizacion.alojamiento.nombre}</b> con el código <b>${res.localizacion.codigo}</b>`
            );
          } else {
            alert("Ocurrio un error al asignar el inmueble");
            this.recargar();
            this.common._setLoading(false);
          }
          this.limpiarVariables();
        })
        .catch((error) => {
          alert("Ocurrio un error con los servicios. Contacte con el administrador");
          this.recargar();
          this.common._setLoading(false);
        });
    } else if (this.opcionSeleccionada.perfilNombre == "EI") {
      this.perfilService.asignarEstablecimentoAAnalista(this.idSeleccion, this.opcionSeleccionada.empresa, 0, this.idJefe)
        .then((res: EstablecimientoPorAnalista) => {
          if (res != null) {
            this.enviarCorreo(
              res.jefe.nombreUsuario,
              res.usuarioSistema.id,
              "Asignación",
              true,
              ` el representante <b>${res.empresa.nombreRepresentante} ${res.empresa.primerApe} ${res.empresa.segundoApe}</b> con la razón social <b>${res.empresa.razonSocial}</b>`
            );
          } else {
            alert("Ocurrio un error al asignar la empresa");
            this.recargar();
            this.common._setLoading(false);
          }
          this.limpiarVariables();
        })
        .catch((error) => {
          alert("Ocurrio un error con los servicios. Contacte con el administrador");
          this.recargar();
          this.common._setLoading(false);
        });
    }
  }

  quitarAsignacionInmuebleAAnalista() {
    this.common._setLoading(true);
    let analistaId = this.opcionSeleccionada.analista;
    if (this.opcionSeleccionada.perfilNombre == "HNT") {
      this.perfilService.quitarAsignacionEstablecimentoAAnalista(analistaId, 0, this.opcionSeleccionada.inmueble, this.idJefe)
        .then((res: EstablecimientoPorAnalista) => {
          if (res != null) {
            this.enviarCorreo(
              res.jefe.nombreUsuario,
              analistaId,
              "Eliminación de asignación",
              false,
              ` el nombre <b>${res.localizacion.alojamiento.nombre}</b> con el código <b>${res.localizacion.codigo}</b>`
            );
          } else {
            alert("Ocurrio un error al asignar el inmueble");
            this.recargar();
            this.common._setLoading(false);
          }
          this.limpiarVariables();
        })
        .catch((error) => {
          alert("Ocurrio un error con los servicios. Contacte con el administrador");
          this.recargar();
          this.common._setLoading(false);
        });
    } else if (this.opcionSeleccionada.perfilNombre == "EI") {
      this.perfilService.quitarAsignacionEstablecimentoAAnalista(analistaId, this.opcionSeleccionada.empresa, 0, this.idJefe)
        .then((res: EstablecimientoPorAnalista) => {
          if (res != null) {
            this.limpiarVariables();
            this.enviarCorreo(
              res.jefe.nombreUsuario,
              analistaId,
              "Eliminación de asignación",
              true,
              ` el representante <b>${res.empresa.nombreRepresentante} ${res.empresa.primerApe} ${res.empresa.segundoApe}</b> con la razón social <b>${res.empresa.razonSocial}</b>`
            );
          } else {
            alert("Ocurrio un error al asignar la empresa");
            this.recargar();
            this.common._setLoading(false);
            this.limpiarVariables();
          }
        })
        .catch((error) => {
          alert(
            "Ocurrio un error con los servicios. Contacte con el administrador"
          );
          this.recargar();
          this.common._setLoading(false);
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
    this.idSeleccion = null;
  }

  mostrarOpcionAsignacion(perfiles: PerfilView) {
    return !perfiles.aprobado && perfiles.analista == null && !perfiles.desafiliado;
  }

  mostrarOpcionQuitarAsignacion(perfiles: PerfilView) {
    return !perfiles.aprobado && perfiles.analista != null && !perfiles.desafiliado;
  }

  enviarCorreo(jefe: string, idUsuarioDestinatario: number, asuntoCorreo: string, isEmpresa: boolean, detalle: string) {
    let asunto = asuntoCorreo + ` de ${isEmpresa ? "empresa" : "inmueble"}`;
    let cuerpo = `El usuario <b>${jefe}</b> le ha 
    ${asuntoCorreo == "Asignación"
        ? "asignado"
        : "eliminado la asignación"} 
      ${isEmpresa
        ? " la empresa con" + detalle + ""
        : " el inmueble con " + detalle + ""
      }`;
    this.perfilService.enviarCorreoUsuario(idUsuarioDestinatario, asunto, cuerpo)
      .then((respuesta) => {
        alert(`${respuesta.estado} de notificación al analista`);
        this.recargar();
        this.common._setLoading(false);
      })
      .catch((error) => {
        alert("Se asignó/quitó la asignación éxitosamente, pero ocurrió un problema al enviar el correo de notificación");
        this.recargar();
        this.common._setLoading(false);
      });
  }
}
