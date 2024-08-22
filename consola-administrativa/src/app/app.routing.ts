import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { ReporteGraficosComponent } from './reporte-graficos/reporte-graficos.component';
import { ReporteHaciendaComponent } from './reporte-hacienda/reporte-hacienda.component';
import { UsuarioComponent } from './usuario/usuario.component';

const routes: Routes =[ 
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      
      {
      path: '',
      loadChildren: './layouts/admin-layout/admin-layout.module#AdminLayoutModule'
    },
    {
      path: 'reporte-hacienda',
      component: ReporteHaciendaComponent
    },
    {
      path: 'reporte-graficos',
      component: ReporteGraficosComponent
    },
    {
      path: 'usuario',
      component: UsuarioComponent
    }]
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
       useHash: true
    })
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
