import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { OAuthHelperService } from '../services/o-auth-herlper.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private authService: OAuthHelperService, private router: Router) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let valido = this.authService.sesionValida();
    if (!valido) {
      this.router.navigate(['login']);
    }
    return valido;
  }
}
