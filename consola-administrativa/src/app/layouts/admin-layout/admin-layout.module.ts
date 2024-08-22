import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { MatButtonModule } from '@angular/material/button';
import { MatRippleModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatSelectModule } from '@angular/material/select';
import { MatSortModule } from "@angular/material/sort";
import { MatTableModule } from "@angular/material/table";
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterModule } from '@angular/router';
import { AccumulationAnnotationService, AccumulationChartModule, AccumulationDataLabelService, AccumulationLegendService, AccumulationTooltipService, PieSeriesService } from '@syncfusion/ej2-angular-charts';
import { DataTablesModule } from 'angular-datatables';
import { TextMaskModule } from 'angular2-text-mask';
import { AsignarClientePorAnalistaComponent } from 'app/asignarClientePorAnalista/asignarClientePorAnalista.component';
import { BeneficiosComponent } from 'app/beneficios/beneficios.component';
import { GestionarBeneficiosComponent } from 'app/beneficios/gestionar-beneficios/gestionar-beneficios.component';
import { MatPaginatorIntlEs } from 'app/languaje';
import { PerfilesComponent } from 'app/perfiles/perfiles.component';
import { ReporteGraficosComponent } from 'app/reporte-graficos/reporte-graficos.component';
import { ReporteHaciendaComponent } from 'app/reporte-hacienda/reporte-hacienda.component';
import { RolesComponent } from 'app/roles/roles.component';
import { GestionarSolicitudesComponent } from 'app/solicitudes/gestionar-solicitudes/gestionar-solicitudes.component';
import { SolicitudesComponent } from 'app/solicitudes/solicitudes.component';
import { GestionarTipoServiciosComponent } from 'app/tipo-servicios/gestionar-tipo-servicios/gestionar-tipo-servicios.component';
import { TipoServiciosComponent } from 'app/tipo-servicios/tipo-servicios.component';
import { UsuarioComponent } from 'app/usuario/usuario.component';
import { GestionarUsuariosComponent } from 'app/usuarios/gestionar-usuarios/gestionar-usuarios.component';
import { UsuariosComponent } from 'app/usuarios/usuarios.component';
import { AdminLayoutRoutes } from './admin-layout.routing';

import { NgxLoadingModule } from 'ngx-loading';

import { ClientesPendientesDeDesafiliarComponent } from 'app/clientesPendientesDeDesafiliar/clientesPendientesDeDesafiliar.component';
import { LoadingComponent } from 'app/loading/loading.component';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    MatIconModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatTableModule,
    TextMaskModule,
    MatAutocompleteModule,
    MatExpansionModule,
    DataTablesModule.forRoot(),
    AccumulationChartModule,
    NgxLoadingModule.forRoot({}),
  ],
  declarations: [
    BeneficiosComponent,
    PerfilesComponent,
    AsignarClientePorAnalistaComponent,
    ClientesPendientesDeDesafiliarComponent,
    RolesComponent,
    SolicitudesComponent,
    TipoServiciosComponent,
    UsuarioComponent,
    UsuariosComponent,
    ReporteHaciendaComponent,
    GestionarBeneficiosComponent,
    GestionarSolicitudesComponent,
    GestionarTipoServiciosComponent,
    GestionarUsuariosComponent,
    ReporteGraficosComponent,
   // LoadingComponent

  ],
  entryComponents: [
    LoadingComponent
  ],
  providers: [{ provide: MatPaginatorIntl, useClass: MatPaginatorIntlEs}, PieSeriesService, AccumulationLegendService, AccumulationTooltipService, AccumulationDataLabelService,
    AccumulationAnnotationService],
})

export class AdminLayoutModule {}
