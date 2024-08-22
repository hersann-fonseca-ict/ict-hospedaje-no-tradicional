package com.ict.notificaciones.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService implements INotificacionesService {

	@Autowired
	private JavaMailSender sender;

	 @Value("${URLSISTEMA}")
	 private String urlSistema;
	
	@Override
	public void enviarCorreo(String destino, String asunto, String tipo) {
		if (destino != null || destino != "") {
			String html = this.generarCuerpoCorreo(tipo, null);
			herramientaCorreo(html, destino, asunto);
		}
	}
	
	@Override
	public void enviarCorreoParam(String destino, String asunto, String tipo, String param) {
		if (destino != null || destino != "") {
			String html = this.generarCuerpoCorreo(tipo, param);
			herramientaCorreo(html, destino, asunto);	
		}
	}

	
	private void herramientaCorreo(String html, String email, String subject) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setText(html, true);
			helper.setSubject(subject);
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private String generarCuerpoCorreo(String tipo, String param) {
		String cuerpo = "";
		if(tipo.equalsIgnoreCase("registroUsuario")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section-->\n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" +
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>El Instituto Costarricense de Turismo le da la bienvenida a la plataforma de Registro de Empresas y Actividades Tur&iacute;sticas. Gracias por registrase, su usuario se ha creado exitosamente.</p><br>\n" +
					"											<p>Su código de verificación es: <strong>"+ param+"</strong></p><br>\n"+
					"											<span>Para continuar con su registro</span> \n" +
					"											<a href=\""+this.urlSistema+"\" target=\"_blank\" style=\"text-decoration:underline;\">Inicie sesi&oacute;n</a>\n" +
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Hasta pronto,</p>\n" +
					"											<p>Departamento de Gestión y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}else if(tipo.equalsIgnoreCase("registroPerfil")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\" style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section-->\n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" +
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>Gracias por registrar su servicio en el Registro de Empresas y Actividades Tur&iacute;sticas. Su perfil se ha completado exitosamente. </p><br>\n" +
					"											<span>Para realizar actualizaciones o modificaciones a su informaci&oacute;n por favor </span>\n" +
					"											<a href=\""+this.urlSistema+"\" target=\"_blank\" style=\"text-decoration:underline;\">Inicie sesi&oacute;n</a>\n" +
					"											<span>nuevamente.</span>\n" +
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}else if(tipo.equalsIgnoreCase("modificacionPerfil")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>Su perfil se ha modificado exitosamente. </p><br>\n" +
					"											<span>Para realizar actualizaciones o modificaciones a su informaci&oacute;n por favor </span>\n" + 
					"											<a href=\""+this.urlSistema+"\" target=\"_blank\" style=\"text-decoration:underline;\">Inicie sesi&oacute;n</a>\n" +
					"											<span>nuevamente.</span>\n" +
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}else if(tipo.equalsIgnoreCase("solicitudCodigo")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>El código de verificación solicitado es: <strong>"+ param +"</strong> </p><br>\n" +
					" 											<p>Utilice este código para cambiar su contraseña</p><br>\n"	+
					"											<span>Para ingresar al sistema: </span>\n" + 
					"											<a href=\""+this.urlSistema+"\" target=\"_blank\" style=\"text-decoration:underline;\">Inicie sesi&oacute;n</a>\n" +
					"											<span>nuevamente.</span>\n" +
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>"; 
		}else if(tipo.equalsIgnoreCase("cambioContra")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>Su contraseña ha sido cambiada</p><br>\n" + 
					"											<span>Para ingresar al sistema: </span>\n" + 
					"											<a href=\""+this.urlSistema+"\" target=\"_blank\" style=\"text-decoration:underline;\">Inicie sesi&oacute;n</a>\n" +
					"											<span>nuevamente.</span>\n" +
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>"; 
		}else if(tipo.equalsIgnoreCase("aprobar")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>"+ param +"</p><br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}else if(tipo.equalsIgnoreCase("asignar")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>"+ param +"</p><br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}else if(tipo.equalsIgnoreCase("desafiliar")) {
			String[] mensajes = param.split("-");
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>"+ mensajes[0]+"</p>" +
					"											<span>"+ mensajes[1]+"</span>\n" +
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}else if(tipo.equalsIgnoreCase("rechazar")) {
			String[] mensajes = param.split("-");
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>"+ mensajes[0] +"</p>\n" +
					"                                           <span>" + mensajes[1] + "</span><br><br>\n" +
					"											<span>"+ mensajes[2] +"</span>\n" + 
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}else if(tipo.equalsIgnoreCase("nuevoUsuarioToken")) {
			cuerpo = "<!DOCTYPE html>\n" +
					"<html lang=\"es\">\n" +
					"<head>\n" +
					"<title>ICT COMUNICADO</title>\n" +
					"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
					"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
					"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" +
					"	<style>\n" +
					"	body {\n" +
					"		argin: 0 !important;\n" +
					"		padding: 0 !important;\n" +
					"	}\n" +
					"	img {\n" +
					"		border: 0 !important;\n" +
					"		outline: none !important;\n" +
					"	}\n" +
					"	p {\n" +
					"		margin: 0px !important;\n" +
					"		padding: 0px !important;\n" +
					"	}\n" +
					"	table {\n" +
					"		border-collapse: collapse;\n" +
					"		border-spacing: 0px;\n" +
					"	}\n" +
					"	table.center {\n" +
					"		margin-left:auto;\n" +
					"		margin-right:auto;\n" +
					"		vertical-align: text-top;\n" +
					"	}\n" +
					"	td, a, span {\n" +
					"		border-collapse: collapse;\n" +
					"		padding: 0px;\n" +
					"	}\n" +
					"	@media only screen and (min-width:481px) and (max-width:699px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" + 
					"		}\n" +
					"		.em_h20 {\n" +
					"			height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"	}\n" +
					"	@media screen and (max-width: 480px) {\n" +
					"		.tabla {\n" +
					"			width: 100% !important;\n" +
					"		}\n" +
					"		.imagen {\n" +
					"			width: 100% !important;\n" +
					"			height: auto !important;\n" +
					"		}\n" +
					"		.em_h20 {\n" +
					"		height: 20px !important;\n" +
					"		}\n" +
					"		.encabezado {\n" +
					"			padding: 20px 10px !important;\n" +
					"		}\n" +
					"		.em_text1 {\n" +
					"			font-size: 16px !important;\n" +
					"			line-height: 24px !important;\n" +
					"		}\n" +
					"	}\n" +
					"</style>\n" +
					"</head>\n" +
					"<body class=\"em_body\" style=\"margin:0px; padding:0px;background-color:#efefef;text-align:center;\">\n" +
					"<table class=\"em_full_wrap\"     style=\"background-color:#efefef;width:100%;\">\n" +
					"		<tbody>\n" +
					"		<tr>\n" +
					"			<td>\n" +
					"			<table class=\"tabla center\" style=\"width:500px;\">\n" +
					"			<!--Header section-->\n" +
					"				<tbody>\n" +
					"					<!--//Header section--> \n" +
					"					<!--Banner section-->\n" +
					"					<tr>\n" +
					"						<td>\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td align=\"center\" valign=\"center\"><img class=\"imagen\" alt=\"ict beneficios\" style=\"display:block; font-family:Arial, sans-serif; line-height:34px; color:#000000; max-width:550px; max-height: 250px;\" src=\"https://fedesurfcr.com/wp-content/uploads/2018/04/Sin.jpg\"></td>\n" + 
					"									</tr>\n" +
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				<!--//Banner section-->\n" +
					"				<!--Content Text Section-->\n" +
					"					<tr>\n" +
					"						<td style=\"padding:35px 70px 30px; background-color:#ffffff;\" class=\"encabezado\">\n" +
					"							<table style=\"width:100%\">\n" +
					"								<tbody>\n" +
					"									<tr>\n" +
					"										<td style=\"font-family:Arial, sans-serif; font-size:16px; line-height:30px; text-align:left;\">\n" +
					"											<p>Hola,</p>\n" +
					"											<p>El código de verificación para acceder al sistema de Hospedaje no tradicional es: <strong>"+ param +"</strong> </p><br>\n" +
					" 											<p>Utilice este código para ingresar o cambiar su contraseña del sistema de Hospedaje no tradicional.</p><br>\n"	+
					"											<span>Para ingresar al sistema: </span>\n" + 
					"											<a href=\""+this.urlSistema+"\" target=\"_blank\" style=\"text-decoration:underline;\">Inicie sesi&oacute;n</a>\n" +
					"											<span>nuevamente.</span>\n" +
					"											<br>\n" +
					"											<br>\n" +
					"											<p>Dese&aacute;ndole lo mejor, </p>\n" +
					"											<p>Departamento de Gesti&oacute;n y Asesoría Tur&iacute;stica </p>\n" +
					"										</td>\n" +
					"									</tr>\n" +					
					"								</tbody>\n" +
					"							</table>\n" +
					"						</td>\n" +
					"					</tr>\n" +
					"				</tbody>\n" +
					"			</table>\n" +
					"		</td>\n" +
					"		</tr>\n" +
					"	</tbody>\n" +
					"	</table>\n" +
					"</body>\n" +
					"</html>";
		}
		
		return cuerpo;
	}
}
