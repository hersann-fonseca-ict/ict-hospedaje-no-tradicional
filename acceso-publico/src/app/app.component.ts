import { Component, OnInit } from '@angular/core';
import { OAuthHelperService } from './services/o-auth-herlper.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'acceso-publico';

  public estaAutenticado: boolean = false;

  constructor(private oauth: OAuthHelperService) { }

  ngOnInit(): void {
    this.estaAutenticado = this.oauth.sesionValida();
  }
}