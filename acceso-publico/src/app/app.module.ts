import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CuerpoComponent } from './cuerpo/cuerpo.component';
import { RegistroComponent } from './registro/registro.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatStepperModule } from '@angular/material/stepper';
import { MatIconModule } from '@angular/material/icon';
import { SolicitudComponent } from './solicitud/solicitud.component';
import { AuthGuard } from './auth/auth.guard';
import { LoginService } from './services/login.service';
import { TextMaskModule } from 'angular2-text-mask';
import { HashLocationStrategy, LocationStrategy } from '@angular/common'; 
import {MatExpansionModule} from '@angular/material/expansion';
import { UsuariosAfiliadosComponent } from './usuarios-afiliados/usuarios-afiliados.component';
import { DataTablesModule } from "angular-datatables";
import { LoadingComponent } from './loading/loading.component';

import { NgxLoadingModule } from 'ngx-loading';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CuerpoComponent,
    RegistroComponent,
    FooterComponent,
    SolicitudComponent,
    UsuariosAfiliadosComponent,
    LoadingComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    TextMaskModule,
    HttpClientModule,
    ReactiveFormsModule,
    NoopAnimationsModule,
    MatStepperModule,
    MatIconModule, 
    MatExpansionModule,
    DataTablesModule,
    NgxLoadingModule.forRoot({})
  ],
  exports: [
    LoginComponent,
    FooterComponent,
    CuerpoComponent
  ],
  providers: [LoginService, AuthGuard, { provide: LocationStrategy, useClass: HashLocationStrategy }],
  bootstrap: [AppComponent]
})
export class AppModule { }
