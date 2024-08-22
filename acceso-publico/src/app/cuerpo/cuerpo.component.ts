import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cuerpo',
  templateUrl: './cuerpo.component.html',
  styleUrls: ['./cuerpo.component.css']
})
export class CuerpoComponent implements OnInit {
  nombreUsuario: string | null = '';
  estaAutentivado: boolean = false;

  constructor(private loginService: LoginService, private route: ActivatedRoute,
    private router: Router) {
    this.nombreUsuario = loginService.obtenerUsuario();
    if (loginService.obtenerToken()) {
      this.estaAutentivado = true;
    }
  }

  ngOnInit(): void {
  }

  cerrarSesion() {
    this.loginService.cerrarSesion();
  }

  irSolicitud() {
    setTimeout(() => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigate(['/solicitud']);
    }, 1000);
  }

  irPerfil() {
    setTimeout(() => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigate(['/registro']);
    }, 1000);
  }

  irUsuariosAfiliados() {
    setTimeout(() => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigate(['/usuarios-afiliados']);
    }, 1000);
  }
}
