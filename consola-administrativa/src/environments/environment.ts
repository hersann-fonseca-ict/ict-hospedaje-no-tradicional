// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  clientId: "asdasdasdasdasdasd",
  clientSecret: "asddasasdasdasdasd",

  /***************URL de Servicios**************/
  oauthURL: "http://localhost:8090/api/security/oauth/token",
  servicioUsuarios: "http://localhost:8090/api/usuarios",
  servicioRoles: "http://localhost:8090/api/roles",
  servicioBeneficio: "http://localhost:8090/api/beneficios",
  servicioTipoServicio: "http://localhost:8090/api/tipo-servicios",
  servicioSolicitud: "http://localhost:8090/api/solicitudes",
  servicioArchivo: "http://localhost:8090/api/archivos",
  servicioPerfiles: "http://localhost:8090/api/perfiles",
  servicioLocalizacion: "http://localhost:8090/api/localizacion-inmueble",
  servicioAlojamiento: "http://localhost:8090/api/alojamiento",
  /***************URL de Servicios**************/

  /***************Parametros de login con Offcie 365**************/
  authorityMSAL : "https://login.microsoftonline.com/e37fb653-7e0c-4718-8038-ee2d8438b6d6", 
  clientIdMSAL :"d598b894-3205-44d9-b70d-4c3d51a99057", 
  redirectUriMSAL:"http://localhost:4201"
  /***************Parametros de login con Offcie 365**************/

};
