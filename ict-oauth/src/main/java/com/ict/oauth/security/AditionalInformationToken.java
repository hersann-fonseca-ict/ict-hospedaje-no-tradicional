package com.ict.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.ict.commons.entity.Perfil;
import com.ict.commons.entity.UsuarioSistema;

//import com.ict.oauth.fegins.IOpcionMenuFeign;

import com.ict.oauth.fegins.IPerfilFeign;
import com.ict.oauth.fegins.IUsuarioFeign;

@Component
public class AditionalInformationToken implements TokenEnhancer {

	@Autowired
	private IPerfilFeign perfilFeign;

	@Autowired
	private IUsuarioFeign usuarioFeign;

	// @Autowired
	// private IOpcionMenuFeign opcionMenuFeign;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		String user = null;
		user = authentication.getName();
		String Office = authentication.getOAuth2Request().getRequestParameters().get("office");
		String consolaAdministrativa = authentication.getOAuth2Request().getRequestParameters().get("consola-administrativa");
		if (Office != null && Office.equals("1")) {
			if (authentication.getName().contains("&")) {
				String[] vector = authentication.getName().split("&");
				if (vector.length > 0) {
					String ban = vector[1];
					Boolean sistemaCA = ban.equals("OCA");
					user = sistemaCA ? vector[0] : authentication.getName();
				}
			}
		} else {
			user = authentication.getName();
		}
		UsuarioSistema usuario = this.usuarioFeign.obtenerPorNombreUsuario(user);
		// Info usuario
		info.put("correo", usuario.getCorreo());
		info.put("id", usuario.getId());
		if (consolaAdministrativa == null) {
			Perfil perfil = perfilFeign.obtenerPorNombreUsuario(user);
			if (perfil != null) {
				info.put("tipoPersona", perfil.getPersonaFisica() != null ? "Persona Física" : "Persona Jurídica");
				if (perfil.getPersonaFisica() != null) {
					// Persona fisica
					info.put("identificacion", perfil.getPersonaFisica().getIdentificacion());
					info.put("nombre", perfil.getPersonaFisica().getNombre());
					info.put("apellido1", perfil.getPersonaFisica().getApellido1());
					info.put("apellido2", perfil.getPersonaFisica().getApellido2());
				} else if (perfil.getPersonaJuridica() != null) {
					// persona juridica
					info.put("cedJuridico", perfil.getPersonaJuridica().getCedJuridico());
					info.put("nombreComercial", perfil.getPersonaJuridica().getNombreComercial());
					info.put("nombreRepresentante", perfil.getPersonaJuridica().getNombreRepresentante());
					info.put("apellido1Representante", perfil.getPersonaJuridica().getApellido1Representante());
					info.put("apellido2Representante", perfil.getPersonaJuridica().getApellido2Representante());
				} else if (perfil.getEmpresa() != null) {
					// empresa
					info.put("razonSocial", perfil.getEmpresa().getRazonSocial());
					info.put("nombreRepresentante", perfil.getEmpresa().getNombreRepresentante());
					info.put("primerApellido", perfil.getEmpresa().getPrimerApe());
					info.put("segundoApellido", perfil.getEmpresa().getSegundoApe());
					info.put("correo", perfil.getEmpresa().getCorreo());
					info.put("url", perfil.getEmpresa().getUrl());
					info.put("codigoPostal", perfil.getEmpresa().getCodigoPostal());
				}
				// tipo de servicio
				info.put("tipoServicio", perfil.getCodigoTipoServicio());
			}
		}
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
