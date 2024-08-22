import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DataTableDirective } from 'angular-datatables';
import { Beneficio } from 'app/services/Beneficios/beneficios';
import { BeneficiosService } from 'app/services/Beneficios/beneficios.service';
import { OAuthHelperService } from 'app/services/OAuthHerlper/oauth-helper.service';
import { DataTablesResponse } from 'app/table-list/datatableRespose';
import { LenguajeTabla } from 'app/table-list/lenguajeTabla';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-beneficios',
  templateUrl: './beneficios.component.html',
  styleUrls: ['./beneficios.component.css']
})
export class BeneficiosComponent implements OnInit {
  public listaBeneficios: [Beneficio];
  beneficios: Beneficio[];
  roles: string[];
  nuevoBeneficio: Beneficio = new Beneficio();
  crear: boolean = false;

  constructor(private beneficioService: BeneficiosService,private http: HttpClient, private oauth: OAuthHelperService, private router: Router) {
    this.roles = this.beneficioService.obtenerRoles();
   }
  totalElements: number = 0;
  todos: Beneficio[] = [];
  loading: boolean;
  dtOptions: DataTables.Settings = {};
  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;
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
          .post<DataTablesResponse>(environment.servicioBeneficio + "/obtenerTabla",
          dataTablesParameters, cabecera
        ).subscribe(resp => {
          this.beneficios = resp.data;
          callback({
            recordsTotal: resp.recordsTotal,
            recordsFiltered: resp.recordsFiltered,
            data: []
          });
        });
    },
    columns: [{ data: 'id' }, { data: 'nombreBeneficio' }, { data: 'activo' },{ data: "fechaCreacion" }]
  };  
}

ngAfterViewInit(): void {
  this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
    dtInstance.ajax.reload()
  });
}
  crearNuevoBeneficio(){
    this.router.navigateByUrl('/gestionar-beneficio');
  }
  modificarBeneficio(beneficio: Beneficio){
      this.router.navigate(['/gestionar-beneficio'], { queryParams: { id: beneficio.id } }); 
  }
  activarInactivar(beneficio: Beneficio, activo){
    beneficio.activo = activo;
    this.beneficioService.guardarBeneficio(beneficio).then( (respuesta: Beneficio) => {
      this.nuevoBeneficio = respuesta;
       }  )
      .catch( 
        
        (err) => alert(err)
        
        
        );

  }
}

