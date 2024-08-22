import { Routes } from '@angular/router';

import { AsignarClientePorAnalistaComponent } from 'app/asignarClientePorAnalista/asignarClientePorAnalista.component';
import { BeneficiosComponent } from 'app/beneficios/beneficios.component';
import { GestionarBeneficiosComponent } from 'app/beneficios/gestionar-beneficios/gestionar-beneficios.component';
import { ClientesPendientesDeDesafiliarComponent } from 'app/clientesPendientesDeDesafiliar/clientesPendientesDeDesafiliar.component';
import { PerfilesComponent } from 'app/perfiles/perfiles.component';
import { ReporteGraficosComponent } from 'app/reporte-graficos/reporte-graficos.component';
import { ReporteHaciendaComponent } from 'app/reporte-hacienda/reporte-hacienda.component';
import { RolesComponent } from 'app/roles/roles.component';
import { AuthGuard } from 'app/services/OAuthHerlper/auth-guard';
import { GestionarSolicitudesComponent } from 'app/solicitudes/gestionar-solicitudes/gestionar-solicitudes.component';
import { SolicitudesComponent } from 'app/solicitudes/solicitudes.component';
import { GestionarTipoServiciosComponent } from 'app/tipo-servicios/gestionar-tipo-servicios/gestionar-tipo-servicios.component';
import { TipoServiciosComponent } from 'app/tipo-servicios/tipo-servicios.component';
import { UsuarioComponent } from 'app/usuario/usuario.component';
import { GestionarUsuariosComponent } from 'app/usuarios/gestionar-usuarios/gestionar-usuarios.component';
import { UsuariosComponent } from 'app/usuarios/usuarios.component';



export const AdminLayoutRoutes: Routes = [
    {
        path: 'beneficios', component: BeneficiosComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-BENEFICIO'],
            parent: ''
        }
    },
    {
        path: 'perfiles', component: PerfilesComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-PERFIL'],
            parent: ''
        }
    },
    {
        path: 'roles', component: RolesComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-ROL'],
            parent: ''
        }
    },
    {
        path: 'solicitudes', component: SolicitudesComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-SOLICITUD'],
            parent: ''
        }
    },
    {
        path: 'tipo-servicios', component: TipoServiciosComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-TIPO-PERFIL'],
            parent: ''
        }
    },

    {
        path: 'usuarios', component: UsuariosComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-USUARIO'],
            parent: ''
        }
    },
    {
        path: 'reporte-hacienda', component: ReporteHaciendaComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'REPORTE-HACIENDA', 'REPORTE'],
            parent: ''
        }
    },

    {
        path: 'usuario', component: UsuarioComponent, canActivate: [AuthGuard], data: {
            role: ['ADMIN', 'REPORTE-HACIENDA', 'REPORTE', 'ANALISTA', 'JEFATURA'],
            parent: ''
        }
    },

    {
        path: 'gestionar-beneficio', component: GestionarBeneficiosComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'CREAR-BENEFICIO', 'MODIFICAR-BENEFICIO'],
            parent: ''
        }
    },
    {
        path: 'gestionar-solicitud', component: GestionarSolicitudesComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'MODIFICAR-SOLICITUD'],
            parent: ''
        }
    },
    {
        path: 'gestionar-tipoServicio', component: GestionarTipoServiciosComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'MODIFICAR-TIPO-PERFIL', 'CREAR-TIPO-PERFIL'],
            parent: ''
        }
    },
    {
        path: 'gestionar-usuarios', component: GestionarUsuariosComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'CREAR-USUARIO', 'MODIFICAR-USUARIO'],
            parent: ''
        }
    },
    {
        path: 'reporte-graficos', component: ReporteGraficosComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'REPORTE-GRAFICOS', 'REPORTE'],
            parent: ''
        }
    },
    {
        path: 'asignar-analista', component: AsignarClientePorAnalistaComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-PERFIL'],
            parent: ''
        }
    },
    {
        path: 'desafiliar', component: ClientesPendientesDeDesafiliarComponent, canActivate: [AuthGuard],
        data: {
            role: ['ADMIN', 'LISTAR-PERFIL'],
            parent: ''
        }
    },
];