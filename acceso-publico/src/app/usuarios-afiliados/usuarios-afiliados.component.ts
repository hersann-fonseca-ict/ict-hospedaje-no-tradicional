import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PerfilView } from '../models/perfil-view';
import { PerfilService } from '../services/perfil.service';

@Component({
  selector: 'app-usuarios-afiliados',
  templateUrl: './usuarios-afiliados.component.html',
  styleUrls: ['./usuarios-afiliados.component.css']
})
export class UsuariosAfiliadosComponent implements OnInit {

  dtOptions: DataTables.Settings = {};

  listaEstablecimientos: PerfilView[];

  constructor(private perfilService: PerfilService, private http: HttpClient) { }

  dataTableLanguage() {
    return {
      emptyTable: "No hay datos disponibles en la tabla",
      zeroRecords: '',
      lengthMenu: 'Mostrar _MENU_ elementos',
      search: 'Buscar:',
      info: "Mostrando desde _START_ al _END_ de _TOTAL_ elementos",
      infoEmpty: "Mostrando ningún elemento.",
      infoFiltered: '(filtrados de _MAX_ elementos totales)',
      processing: 'Procesando...',
      loadingRecords: "Cargando registros...",
      paginate: { first: 'Primera', last: 'Última', next: 'Siguiente', previous: 'Anterior' },
      aria: {
        sortAscending: ": Activar para ordenar la tabla en orden ascendente",
        sortDescending: ": Activar para ordenar la tabla en orden descendente"
      }
    };
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      autoWidth: false,
      responsive: true,
      lengthMenu: [5, 25, 50, 100],
      processing: true,
      serverSide: true,
      language: this.dataTableLanguage(),
      ajax: (dataTablesParameters: any, callback) => {
        this.perfilService.obtenerTablaEmpresas(dataTablesParameters)
          .subscribe(resp => {
            this.listaEstablecimientos = resp.data;
            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: []
            });
          });
      },
      columns: [{ data: 'nombre' }, { data: 'identificacion' }, { data: 'domicilioLegal' }, { data: 'nombreRepresentante' }, { data: 'identificacionRepresentante' }, { data: 'provincia' }, { data: 'canton' }, { data: 'distrito' }, { data: 'numHab' }, { data: 'numMaxHuespedes' }]
    };
  }
}