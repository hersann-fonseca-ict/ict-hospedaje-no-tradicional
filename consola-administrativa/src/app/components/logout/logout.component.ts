import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MsalService } from '@azure/msal-angular';
import { CommonService } from 'app/services/common/common.service';
import { OAuthHelperService } from 'app/services/OAuthHerlper/oauth-helper.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor( private authService: MsalService , private common:  CommonService,  private oauth: OAuthHelperService,private router: Router,public dialogRef: MatDialogRef<LogoutComponent>) { }

  ngOnInit(): void {
  }
  cerrarSesion(){

    this.authService.logout(); 
    this.common._setLoading(true); 
    this.oauth.eliminarToken();
   // window.location.reload();
    this.dialogRef.close();
    this.common._setLoading(false); 
   
}
closeModal() {
  this.dialogRef.close();
}
}
