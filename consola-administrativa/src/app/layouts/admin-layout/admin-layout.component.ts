import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { AccountInfo, AuthenticationResult } from '@azure/msal-browser';
import { MsalService } from '@azure/msal-angular';
import { Location, PopStateEvent } from "@angular/common";
import "rxjs/add/operator/filter";
import { Router, NavigationEnd, NavigationStart } from "@angular/router";
import { Subscription } from "rxjs/Subscription";
import PerfectScrollbar from "perfect-scrollbar";
import * as $ from "jquery";
import { OAuthHelperService } from "app/services/OAuthHerlper/oauth-helper.service";
import { CommonService } from "app/services/common/common.service";
import { UsuariosService } from "app/services/Usuarios/usuarios.service";

@Component({
  selector: "app-admin-layout",
  templateUrl: "./admin-layout.component.html",
  styleUrls: ["./admin-layout.component.scss"],
})
export class AdminLayoutComponent implements OnInit {
  private _router: Subscription;
  private lastPoppedUrl: string;
  private yScrollStack: number[] = [];
  loading = false;
  public estaAutenticado: boolean;

  @ViewChild("nombreUsuario") nombreUsuario: ElementRef;
  @ViewChild("clave") clave: ElementRef;

  constructor(private authService: MsalService,
    public location: Location,
    private router: Router,
    private oauth: OAuthHelperService,
    private _common: CommonService,
    private usuarioService: UsuariosService
  ) {
  }

  async ngOnInit() {
    let res = await this.authService.instance.handleRedirectPromise();
    if (res != null && res.account != null) {
      this.authService.instance.setActiveAccount(res.account)
    }
    this.estaAutenticado = !this.isLoggedIn() ? false : this.oauth.sesionValida();
    if (this.estaAutenticado) {
      const isWindows = navigator.platform.indexOf("Win") > -1;
      if (isWindows && !document.getElementsByTagName("body")[0].classList.contains("sidebar-mini")) {
        // if we are on windows OS we activate the perfectScrollbar function
        document.getElementsByTagName("body")[0].classList.add("perfect-scrollbar-on");
      } else {
        document.getElementsByTagName("body")[0].classList.remove("perfect-scrollbar-off");
      }
      const elemMainPanel = <HTMLElement>document.querySelector(".main-panel");
      const elemSidebar = <HTMLElement>document.querySelector(".sidebar .sidebar-wrapper");
      this.location.subscribe((ev: PopStateEvent) => {
        this.lastPoppedUrl = ev.url;
      });
      this.router.events.subscribe((event: any) => {
        if (event instanceof NavigationStart) {
          if (event.url != this.lastPoppedUrl)
            this.yScrollStack.push(window.scrollY);
        } else if (event instanceof NavigationEnd) {
          if (event.url == this.lastPoppedUrl) {
            this.lastPoppedUrl = undefined;
            window.scrollTo(0, this.yScrollStack.pop());
          } else window.scrollTo(0, 0);
        }
      });
      this._router = this.router.events
        .filter((event) => event instanceof NavigationEnd)
        .subscribe((event: NavigationEnd) => {
          elemMainPanel.scrollTop = 0;
          elemSidebar.scrollTop = 0;
        });
      if (window.matchMedia(`(min-width: 960px)`).matches && !this.isMac()) {
        let ps = new PerfectScrollbar(elemMainPanel);
        ps = new PerfectScrollbar(elemSidebar);
      }

      const window_width = $(window).width();
      let $sidebar = $(".sidebar");
      let $sidebar_responsive = $("body > .navbar-collapse");
      let $sidebar_img_container = $sidebar.find(".sidebar-background");

      if (window_width > 767) {
        if ($(".fixed-plugin .dropdown").hasClass("show-dropdown")) {
          $(".fixed-plugin .dropdown").addClass("open");
        }
      }

      $(".fixed-plugin a").click(function (event) {
        // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
        if ($(this).hasClass("switch-trigger")) {
          if (event.stopPropagation) {
            event.stopPropagation();
          } else if (window.event) {
            window.event.cancelBubble = true;
          }
        }
      });

      $(".fixed-plugin .badge").click(function () {
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
        var new_color = $(this).data("color");
        if ($sidebar.length !== 0) {
          $sidebar.attr("data-color", new_color);
        }
        if ($sidebar_responsive.length !== 0) {
          $sidebar_responsive.attr("data-color", new_color);
        }
      });

      $(".fixed-plugin .img-holder").click(function () {
        let $full_page_background = $(".full-page-background");
        $(this).parent("li").siblings().removeClass("active");
        $(this).parent("li").addClass("active");
        var new_image = $(this).find("img").attr("src");
        if ($sidebar_img_container.length != 0) {
          $sidebar_img_container.fadeOut("fast", function () {
            $sidebar_img_container.css(
              "background-image",
              'url("' + new_image + '")'
            );
            $sidebar_img_container.fadeIn("fast");
          });
        }
        if ($full_page_background.length != 0) {
          $full_page_background.fadeOut("fast", function () {
            $full_page_background.css(
              "background-image",
              'url("' + new_image + '")'
            );
            $full_page_background.fadeIn("fast");
          });
        }
        if ($sidebar_responsive.length != 0) {
          $sidebar_responsive.css(
            "background-image",
            'url("' + new_image + '")'
          );
        }
      });
    }else {
      this.oauth.eliminarToken();
    }
    this._common.loadingService.subscribe(data => {
      this.loading = data;
    });
  }

  ngAfterViewInit() {
    if (this.estaAutenticado && this.isLoggedIn()) {
      this.runOnRouteChange();
    }
  }

  isLoggedIn(): boolean {
    return this.authService.instance.getActiveAccount() !== null
  }


  login() {
    this.authService.loginPopup()
      .subscribe((response: AuthenticationResult) => {
        this.autenticar(response.account);
      });
  }

  isMaps(path: string): boolean {
    var title = this.location.prepareExternalUrl(this.location.path());
    title = title.slice(1);
    return path !== title;
  }

  async autenticar(account: AccountInfo) {
    if (account !== null) {
      this._common._setLoading(true);
      if (this.oauth.obtenerTokenAlmacenado()) {
        this.estaAutenticado = true;
        this.router.navigate(['usuario']);
      } else {
        let temp_password: string = account.localAccountId.split('-')[0];
        try {
          let usuario = await this.usuarioService.usuarioNuevoToken(account.username, temp_password, account.name);
          try {
            await this.oauth.autenticar(usuario.correo, temp_password);
            this.authService.instance.setActiveAccount(account);
            this.estaAutenticado = true;
            this.router.navigate(['usuario']);
          } catch (err) {
            if (err.status == '401') {
              alert('El usuario ingresado no existe');
            } else if (err.status == '400') {
              alert('Usuario o contraseña no válidos');
            } else {
              alert('Ha ocurrido un error, contacte con el administrador del sitio');
            }
            this.estaAutenticado = false;
            this.authService.logout();
            this.oauth.eliminarToken();
          }
        } catch (error) {
          if (error.status == '400') {
            alert('El usuario fue registrado pero ocurrió un error al enviar el correo');
            this.authService.instance.setActiveAccount(account);
            this.estaAutenticado = true;
            this.router.navigate(['usuario']);
          } else {
            alert('Ha ocurrido un error, contacte con el administrador del sitio');
            this.estaAutenticado = false;
            this.authService.logout();
            this.oauth.eliminarToken();
          }
        }
      }
      this._common._setLoading(false);
    }
  }

  //alert(err.status == '401' ? 'El usuario ingresado no existe' :  '400' ? 'Usuario o contraseña no válidos' :'Ha ocurrido un error, contacte con el administrador del sitio')
  runOnRouteChange(): void {
    if (this.estaAutenticado && this.isLoggedIn()) {
      if (window.matchMedia("(min-width: 960px)").matches && !this.isMac()) {
        const elemMainPanel = <HTMLElement>(document.querySelector(".main-panel"));
        const ps = new PerfectScrollbar(elemMainPanel);
        ps.update();
      }
    }
  }

  isMac(): boolean {
    return navigator.platform.toUpperCase().indexOf("MAC") >= 0 || navigator.platform.toUpperCase().indexOf("IPAD") >= 0;
  }
}