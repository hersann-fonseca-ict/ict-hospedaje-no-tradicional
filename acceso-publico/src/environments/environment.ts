// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  clientId: "asdasdasdasdasdasd",
  clientSecret: "asddasasdasdasdasd",
  oauthURL: "http://localhost:8090/api/security/oauth/token",
  servicioUsuarios: "http://localhost:8090/api/usuarios",
  servicioNotificaciones: "http://localhost:8090/api/notificaciones",
  servicioRoles: "http://localhost:8090/api/roles",
  servicioBeneficio: "http://localhost:8090/api/beneficios",
  servicioTipoServicio: "http://localhost:8090/api/tipo-servicios",
  servicioSolicitud: "http://localhost:8090/api/solicitudes",
  servicioPerfil: "http://localhost:8090/api/perfiles",
  servicioArchivo: "http://localhost:8090/api/archivos",
  servicioDireccion: "http://localhost:8090/api/direccion",
  servicioAlojamiento: "http://localhost:8090/api/alojamiento",
  servicioLocalizacion: "http://localhost:8090/api/localizacion-inmueble"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
