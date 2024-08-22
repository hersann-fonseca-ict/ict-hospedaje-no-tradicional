package com.ict.notificaciones.services;

public interface INotificacionesService {
	public void enviarCorreo(String destino, String asunto, String cuerpo);
	public void enviarCorreoParam(String destino, String asunto, String tipo, String param);
}
