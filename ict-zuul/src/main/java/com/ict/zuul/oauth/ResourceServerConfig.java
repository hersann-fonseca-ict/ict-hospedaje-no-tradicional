package com.ict.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
			//OAUTH
			authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
			
			//Olvido clave
			.and().authorizeRequests().antMatchers("/api/usuarios/solicitarCodigoVerificacion").permitAll()
			.and().authorizeRequests().antMatchers("/api/usuarios/nuevoCliente").permitAll()
			.and().authorizeRequests().antMatchers("/api/usuarios/cambiarClave").permitAll()
			.and().authorizeRequests().antMatchers("/api/usuarios/obtenerOpcionesMenuPorIdUsuario").permitAll()
			.and().authorizeRequests().antMatchers("/api/usuarios/usuarioNuevoToken").permitAll()
		
			
			//Devs -- SOLO DEV
			//.and().authorizeRequests().antMatchers("**").permitAll()
			//.and().authorizeRequests().antMatchers("/api/**/v2/api-docs").permitAll()
			//.and().authorizeRequests().antMatchers("/api/**/webjars/**").permitAll()
			//.and().authorizeRequests().antMatchers("/api/**/swagger-resources/**").permitAll()
			
			//Cliente
			.and().authorizeRequests().antMatchers("/api/perfiles/nuevoCliente").permitAll()
			
			//Perfiles
			.and().authorizeRequests().antMatchers("/api/perfiles/guardar").hasAnyAuthority("CREAR-USUARIO", "ADMIN","ANALISTA","REPORTE","JEFATURA")//acceso publico
			.and().authorizeRequests().antMatchers("/api/perfiles/guardar").hasAnyAuthority("MODIFICAR-USUARIO", "ADMIN","ANALISTA","REPORTE","JEFATURA")//acceso publico
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerPorNombreUsuario").hasAnyAuthority("LISTAR-USUARIO", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerPorId").hasAnyAuthority("LISTAR-USUARIO", "ADMIN","ANALISTA","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerTabla").hasAnyAuthority("LISTAR-PERFILES", "ADMIN","ANALISTA","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerTablaActivos").hasAnyAuthority("REPORTE-HACIENDA", "ADMIN","REPORTE","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerListadoExcel").hasAnyAuthority("LISTAR-PERFILES", "ADMIN","ANALISTA","REPORTE","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerGraficoTipoServicio").hasAnyAuthority("OBTENER-DATOS-PERFILES", "ADMIN","ANALISTA","REPORTE","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerCantidadPerfiles").hasAnyAuthority("CANTIDAD-PERFILES", "ADMIN","ANALISTA","REPORTE","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/perfiles/obtenerEmpresaPorId").hasAnyAuthority("MODIFICAR-USUARIO", "ADMIN","ANALISTA","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/perfiles/realizarAccionEmpresa").hasAnyAuthority("MODIFICAR-USUARIO", "ADMIN","ANALISTA","JEFATURA")//consola
			//Menu
			.and().authorizeRequests().antMatchers("/api/opcionmenu/obtenerOpcionesMenuPorIdUsuario").hasAnyAuthority("ANALISTA", "ADMIN","ANALISTA","REPORTE","JEFATURA")//consola

			
			
			//Roles
			.and().authorizeRequests().antMatchers("/api/roles/guardar").hasAnyAuthority("CREAR-ROL", "ADMIN")
			.and().authorizeRequests().antMatchers("/api/roles/guardar").hasAnyAuthority("MODIFICAR-ROL", "ADMIN")
			.and().authorizeRequests().antMatchers("**/api/roles/obtener", "/api/roles/obtenerPorId").hasAnyAuthority("LISTAR-ROL", "ADMIN")
			
			//Tipo Servicio
			.and().authorizeRequests().antMatchers("/api/tipo-servicios/guardar").hasAnyAuthority("CREAR-TIPO-PERFIL", "ADMIN")
			.and().authorizeRequests().antMatchers("/api/tipo-servicios/guardar").hasAnyAuthority("MODIFICAR-PERFIL", "ADMIN")
			.and().authorizeRequests().antMatchers("/api/tipo-servicios/obtener", "/api/tipo-servicios/obtenerPorId").hasAnyAuthority("LISTAR-TIPO-PERFIL","LISTAR-SOLICITUD", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			
			//Beneficios
			.and().authorizeRequests().antMatchers("/api/beneficios/guardar").hasAnyAuthority("CREAR-BENEFICIO", "ADMIN")
			.and().authorizeRequests().antMatchers("/api/beneficios/guardar").hasAnyAuthority("MODIFICAR-BENEFICIO", "ADMIN")
			.and().authorizeRequests().antMatchers("/api/beneficios/obtener", "/api/beneficios/obtenerPorId").hasAnyAuthority("LISTAR-BENEFICIO","LISTAR-SOLICITUD", "ADMIN")
			
			//Notificaciones
			.and().authorizeRequests().antMatchers("/api/notificaciones/enviarCorreoParam").hasAnyAuthority("ENVIAR-PARAM-NOTIFICACIONES", "ADMIN","ANALISTA","REPORTE","JEFATURA")//ambos
			.and().authorizeRequests().antMatchers("/api/notificaciones/enviarCorreo").hasAnyAuthority("ENVIAR-NOTIFICACIONES", "ADMIN","ANALISTA","REPORTE","JEFATURA")//ambos
			 
			//Solicitudes
			.and().authorizeRequests().antMatchers("/api/solicitudes/guardar").hasAnyAuthority("CREAR-SOLICITUD", "ADMIN")
			.and().authorizeRequests().antMatchers("/api/solicitudes/guardar").hasAnyAuthority("MODIFICAR-SOLICITUD", "ADMIN")
			.and().authorizeRequests().antMatchers("/api/solicitudes/obtener", "/api/solicitudes/obtenerPorId").hasAnyAuthority("LISTAR-SOLICITUD", "ADMIN")
			
			//Usuarios
			.and().authorizeRequests().antMatchers("/api/usuarios/guardar").hasAnyAuthority("CREAR-USUARIO", "ADMIN","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/usuarios/guardar").hasAnyAuthority("MODIFICAR-USUARIO", "ADMIN","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/usuarios/obtener", "/api/usuarios/obtenerPorNombreUsuario").hasAnyAuthority("LISTAR-USUARIO", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/usuarios/obtener", "/api/usuarios/ObtenerCantidadUsuarios").hasAnyAuthority("CANTIDAD-USUARIOS", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			
			//Alojamientos
			.and().authorizeRequests().antMatchers("/api/alojamiento/guardar").hasAnyAuthority("CREAR-ALOJAMIENTO", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/alojamiento/listado").hasAnyAuthority("LISTADO-ALOJAMIENTO", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/alojamiento/listadoPorPadre").hasAnyAuthority("LISTA-PADRE-ALOJAMIENTO", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/alojamiento/listadoClase").hasAnyAuthority("LISTA-CLASE-ALOJAMIENTO", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/alojamiento/obtenerGraficoAlojamiento").hasAnyAuthority("OBTENER-DATOS-ALOJAMIENTO", "ADMIN","ANALISTA","REPORTE","JEFATURA")//consola
			
			//Localizacion Inmueble
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/guardar").hasAnyAuthority("CREAR-LOCALIZACION-INMUEBLE", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/guardarLista").hasAnyAuthority("CREAR-LISTA-LOCALIZACION-INMUEBLE", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/eliminar").hasAnyAuthority("ELIMINAR-LOCALIZACION-INMUEBLE", "ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/obtenerLista").hasAnyAuthority("OBTENER-LISTA-LOCALIZACION-INMUEBLE","ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/obtenerGraficoPorProvincia").hasAnyAuthority("OBTENER-DATOS-PROVINCIA", "ADMIN","ANALISTA","REPORTE","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/ObtenerPerfilesConInmuebles").hasAnyAuthority("OBTENER-DATOS-INMUEBLES", "ADMIN","ANALISTA","REPORTE","JEFATURA")//consola
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/realizarAccion").hasAnyAuthority("MODIFICAR-LOCALIZACION-INMUEBLE","ADMIN","ANALISTA","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/localizacion-inmueble/enviarCorreo").hasAnyAuthority("ENVIO-CORREO","ADMIN","ANALISTA","JEFATURA")
			
			//Secuencia
			.and().authorizeRequests().antMatchers("/api/secuencia/obtenerProxSec").hasAnyAuthority("OBTENER-SECUENCIA", "ADMIN","ANALISTA","REPORTE","JEFATURA")//acceso publico
			
			//Archivos
			.and().authorizeRequests().antMatchers("/api/archivos/subir","/api/archivos/bajar", "/api/archivos/listar").hasAnyAuthority("MODIFICAR-SOLICITUD","CREAR-SOLICITUD", "ADMIN")
			
			//Direcciones
			.and().authorizeRequests().antMatchers("/api/direccion/obtenerCantonPorId", "/api/direccion/obtenerDistritoPorId","/api/direccion/obtener").hasAnyAuthority("ADMIN","ANALISTA","REPORTE","JEFATURA")
			.and().authorizeRequests().antMatchers("/api/direccion/obtenerNombreCanton", "/api/direccion/obtenerNombreDistrito").hasAnyAuthority("ADMIN","ANALISTA","REPORTE","JEFATURA")
			
			
			.and().authorizeRequests().anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}

}