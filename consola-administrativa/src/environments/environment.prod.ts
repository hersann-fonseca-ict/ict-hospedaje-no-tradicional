// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  clientId: "asdasdasdasdasdasd",
  clientSecret: "asddasasdasdasdasd",

  /***************URL de Servicios**************/
  oauthURL: "https://hospedajent.ict.go.cr:8090/api/security/oauth/token",
  servicioUsuarios: "https://hospedajent.ict.go.cr:8090/api/usuarios",
  servicioRoles: "https://hospedajent.ict.go.cr:8090/api/roles",
  servicioBeneficio: "https://hospedajent.ict.go.cr:8090/api/beneficios",
  servicioTipoServicio: "https://hospedajent.ict.go.cr:8090/api/tipo-servicios",
  servicioSolicitud: "https://hospedajent.ict.go.cr:8090/api/solicitudes",
  servicioArchivo: "https://hospedajent.ict.go.cr:8090/api/archivos",
  servicioPerfiles: "https://hospedajent.ict.go.cr:8090/api/perfiles",
  servicioLocalizacion: "https://hospedajent.ict.go.cr:8090/api/localizacion-inmueble",
  servicioAlojamiento: "https://hospedajent.ict.go.cr:8090/api/alojamiento",
  /***************URL de Servicios**************/

  /***************Parametros de login con Offcie 365**************/
  authorityMSAL : "https://login.microsoftonline.com/d07b6535-1ddc-406a-8341-0b0a309d4115", 
  clientIdMSAL :"50cb1990-8ba4-4f55-b790-9fe740826873",
  redirectUriMSAL:"https://hospedajent.ict.go.cr:8095"
  /***************Parametros de login con Offcie 365**************/

};
