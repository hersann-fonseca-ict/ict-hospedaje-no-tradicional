import { NgModule } from '@angular/core';
import {  RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { AppComponent } from './app.component';
import { CuerpoComponent } from './cuerpo/cuerpo.component';
import { AuthGuard } from './auth/auth.guard';
import { SolicitudComponent } from './solicitud/solicitud.component';
import { UsuariosAfiliadosComponent } from './usuarios-afiliados/usuarios-afiliados.component';
 const rutas: Routes = [
  {

    path: '',
    component: CuerpoComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'registro',
        component: RegistroComponent
      },
      {
        path: 'solicitud',
        component: SolicitudComponent
      },
      {
        path: 'usuarios-afiliados',
        component: UsuariosAfiliadosComponent
      }
    ]},
    
    
  {
    path: '',
    component: AppComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent
      }
    ]
  },
  { path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(rutas)],
  exports: [RouterModule]
})
export class AppRoutingModule {}