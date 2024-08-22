package com.ict.archivos.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IArchivoService {
	public void cargar(String ruta, MultipartFile file) throws Exception;
	public Resource descargar(String ruta) throws Exception;
}
