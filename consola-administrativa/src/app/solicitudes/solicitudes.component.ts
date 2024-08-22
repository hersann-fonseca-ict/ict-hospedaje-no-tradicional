import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OAuthHelperService } from 'app/services/OAuthHerlper/oauth-helper.service';
import { Solicitud } from 'app/services/Solicitudes/solicitud';
import { DataTablesResponse } from 'app/table-list/datatableRespose';
import { LenguajeTabla } from 'app/table-list/lenguajeTabla';
import { environment } from 'environments/environment';
import * as XLSX from 'xlsx';
@Component({
  selector: 'app-solicitudes',
  templateUrl: './solicitudes.component.html',
  styleUrls: ['./solicitudes.component.css']
})

export class SolicitudesComponent implements OnInit {

 
  dtOptions: DataTables.Settings = {};
  solicitudes: Solicitud[];
  nombreTipoServicio: string='';
  roles: string[];
  public mascara = [ /\d/, '-', /\d/, /\d/,/\d/,/\d/, '-', /\d/, /\d/, /\d/, /\d/];
  mask: boolean = false;
  public cedulaIdentidadMaskara =  [ /\d/, '-', /\d/, /\d/,/\d/,/\d/, '-', /\d/, /\d/, /\d/, /\d/];
  fileName= 'solicitudes.xlsx';
  constructor( private http: HttpClient, private oauth: OAuthHelperService, private router: Router) {
    this.roles = this.oauth.obtenerRoles();
   }


  ngOnInit() {
    let headers = new HttpHeaders({
      Authorization: `Bearer ${this.oauth.obtenerTokenAlmacenado()}`,
    });
  var cabecera: Object = new Object();
  cabecera['headers'] = headers;
      this.dtOptions = {
        pagingType: 'full_numbers',
        pageLength: 5,
        autoWidth: false,
        responsive:true,
        lengthMenu : [5, 25, 50],
        processing: true,
      serverSide: true,
      language: LenguajeTabla.spanish_datatables,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
            .post<DataTablesResponse>(environment.servicioSolicitud + "/obtenerTabla",
            dataTablesParameters, cabecera
          ).subscribe(resp => {
            this.solicitudes = resp.data;
            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: []
            });
          });
      },
      columns: [{ data: 'id' }, { data: 'nombreComercial' }, { data: 'identificacionComercial' },{ data: "identificacionRepresentante" },
      { data: "nombreRepresentante" },{ data: "apellido1Representante" },{ data: "apellido2Representante" },{ data: "estadoSolicitud" }
      ,{ data: "codigoTipoServicio" }]
    };  

  }

  public redirectToDetails = (id: string) => {
    this.router.navigate(['/gestionar-solicitud'], { queryParams: { id: id }});    
 }
 exportexcel(): void 
    {
       /* table id is passed over here */   
       let element = document.getElementById('tblSolicitudes'); 
       const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(element);

       /* generate workbook and add the worksheet */
       const wb: XLSX.WorkBook = XLSX.utils.book_new();
       XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

       /* save to file */
       XLSX.writeFile(wb, this.fileName);
			
    }
    setTipoFisica(tipoIdentificacion) {
     
      if(tipoIdentificacion.toString().length  == 9){
        this.mask = true; 
        this.mascara=this.cedulaIdentidadMaskara; 
        }else{
          this.mask = false
        }
    }

}

