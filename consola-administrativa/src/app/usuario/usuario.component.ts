import { Component, OnInit } from '@angular/core';
import { MsalService } from '@azure/msal-angular';
import { CommonService } from 'app/services/common/common.service';
import { TokenDecode } from 'app/services/OAuthHerlper/token-decode';

@Component({
  selector: 'app-user-profile',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {
  token: TokenDecode;
  name: string;
  email: string;
  constructor(private authService: MsalService, private _common: CommonService) { }

  ngOnInit() {
    this.name = this.authService.instance.getActiveAccount().name;
    this.email = this.authService.instance.getActiveAccount().username;
  }
}