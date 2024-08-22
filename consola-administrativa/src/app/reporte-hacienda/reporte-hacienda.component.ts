import { Component, OnInit, ViewChild } from '@angular/core';
import { LenguajeTabla } from 'app/table-list/lenguajeTabla';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataTablesResponse } from 'app/table-list/datatableRespose';
import { environment } from 'environments/environment';
import { OAuthHelperService } from 'app/services/OAuthHerlper/oauth-helper.service';
import { PerfilModel } from 'app/services/Perfiles/perfil-model';
import { PerfilesService } from 'app/services/Perfiles/perfiles.service';
import * as XLSX from 'xlsx';
import { PerfilExcel } from 'app/services/Perfiles/perfil-excel';
import { CommonService } from 'app/services/common/common.service';

@Component({
  selector: 'app-reporte-hacienda',
  templateUrl: './reporte-hacienda.component.html',
  styleUrls: ['./reporte-hacienda.component.css']
})
export class ReporteHaciendaComponent implements OnInit {

  dtOptions: DataTables.Settings = {};

  listaPerfiles: PerfilModel[];

  listadoExcel: PerfilExcel[];
  fileNameHNT = 'Reporte_Hacienda_HNT.xlsx';
  fileNameEi = 'Reporte_Hacienda_Empresa.xlsx';

  constructor( private common:  CommonService, private perfilService: PerfilesService, private http: HttpClient, private oauth: OAuthHelperService) { }

  ngOnInit(): void {
    this.common._setLoading(true); 
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
      lengthMenu: [5, 25, 50, 100],
      processing: true,
      serverSide: true,
      language: LenguajeTabla.spanish_datatables,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
          .post<DataTablesResponse>(environment.servicioPerfiles + "/obtenerTablaEmpresas",
            dataTablesParameters, cabecera
          ).subscribe(resp => {            
            if(resp==null){
              this.listaPerfiles = [];
            }else{
              this.listaPerfiles = resp.data;
            }
        
            this.common._setLoading(false);
            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: []
            });
          },error=>{
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
        { data: "telefono" },
        { data: "correo" },        
        { data: "url" },
        { data: "fechaAprobacion" },
      ],
    };
  }

  verificarTipoServicio(perfil: PerfilModel){
    if(perfil.codigoTipoServicio == "EI"){
      return perfil.nombreRepresentanteEmpresa + ' ' + perfil.primerApeEmpresa + ' ' + perfil.segundoApeEmpresa;
    }else if(perfil.codigoTipoServicio == "HNT"){
      if(perfil.idPersonaFisica != null){
        return perfil.nombre +' '+ perfil.apellido1 +' '+ perfil.apellido2
      }else if(perfil.idPersonaJuridica != null){
        return perfil.nombreComercial;
      }
    }
  }

  exportarHNT(){
    this.common._setLoading(true); 
    //Constante para establecer el nombre de las columnas con los titulos
    const fields = [
      'tipo1', 
      'tipo2', 
      "cedula", 
      "nombre1", 
      "nombre2", 
      "identificacionRepresentante", 
      "domicilioLegal", 
      "estado", 
      "codigo", 
      "fechaAprobacion",       
      "provincia", 
      "canton", 
      "distrito", 
      "direccion",
      "numeroHabitaciones",
      "numeroHuespedes", 
      "correo", 
      "telefono",
      "correoNotificaciones",
      "urlEmpresa"
    ]

    //Constate para establecer el tamaño de las columnas del xlsx
    const wscols = [
      { wch: 25 }, //tipo
      { wch: 25 }, //tipo
      { wch: 20 }, //cedula
      { wch: 30 }, //nombre
      { wch: 30 }, //nombre
      { wch: 20 }, //identificacion representante legal
      { wch: 30 }, //domicilio legal
      { wch: 20 }, //estado
      { wch: 10 }, //codigo
      { wch: 15 }, //fecha aprobacion
      { wch: 20 }, //provincia
      { wch: 20 }, //canton
      { wch: 20 }, //distrito
      { wch: 30 }, //direccion
      { wch: 15 }, //numero habitaciones
      { wch: 15 }, //numero huespedes
      { wch: 30 }, //correo
      { wch: 15 }, //telefono
      { wch: 30 }, //correo empresa
      { wch: 30 } //sitio web empresa
    ];

    this.perfilService.obtenerListadoExcel().then((respuesta: PerfilExcel[]) => {
      if (respuesta.length > 0) {
        this.listadoExcel = respuesta;
        const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.listadoExcel, { header: fields , skipHeader:true });

        //tamaño de columnas
        ws['!cols'] = wscols;

        /* generate workbook and add the worksheet */
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

        /* save to file */
        XLSX.writeFile(wb, this.fileNameHNT);
        alert('El reporte se genero con éxito')
        this.common._setLoading(false);
      }else{
        alert('No se encontraron registros aprobados')
        this.common._setLoading(false);
      }
    }).catch(error =>{
      alert('Ocurrio un error al generar el reporte')
      this.common._setLoading(false);
    });
  }

  exportarEi(){
    this.common._setLoading(true); 
    //Constante para establecer el nombre de las columnas con los titulos
    const fields = [
      'tipo1', 
      'tipo2', 
      "identificadorFisicoJuridico",
      'cedulaJuridica',
      "nombre2",
      "nombre1", 
      "cedula", 
      "direccion",
      "provincia", 
      "canton", 
      "distrito", 
      "correoNotificaciones",
      "telefonoEmpresa",
      "urlEmpresa",
      "estado", 
      "fechaAprobacion",       
      "correo"
    ]

    //Constate para establecer el tamaño de las columnas del xlsx
    const wscols = [
      { wch: 25 }, //tipo
      { wch: 25 }, //tipo
      { wch: 30 }, //identificador fisica o juridico
      { wch: 20 }, //cedulaJ
      { wch: 30 }, //nombre2
      { wch: 30 }, //nombre1
      { wch: 20 }, //cedula
      { wch: 30 }, //dirección
      { wch: 15 }, //provincia
      { wch: 20 }, //canton
      { wch: 20 }, //distrito
      { wch: 25 }, //correo not
      { wch: 30 }, //telefonoEmpresa
      { wch: 30 }, //sitio web
      { wch: 15 }, //estado
      { wch: 15 }, //fecha de otorgamiento
      { wch: 25 } //Correos
    ];

    this.perfilService.obtenerListadoExcelEmpresa().then((respuesta: PerfilExcel[]) => {
      if (respuesta.length > 0) {
        this.listadoExcel = respuesta;
        const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.listadoExcel, { header: fields , skipHeader:true });

        //tamaño de columnas
        ws['!cols'] = wscols;

        /* generate workbook and add the worksheet */
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

        /* save to file */
        XLSX.writeFile(wb, this.fileNameEi);
        alert('El reporte se genero con éxito')
        this.common._setLoading(false);
      }else{
        alert('No se encontraron registros aprobados')
        this.common._setLoading(false);
      }
    }).catch(error =>{
      alert('Ocurrio un error al generar el reporte')
      this.common._setLoading(false);
    });
  }
}
