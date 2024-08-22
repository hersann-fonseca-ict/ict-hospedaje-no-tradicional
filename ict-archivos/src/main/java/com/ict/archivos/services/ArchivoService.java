package com.ict.archivos.services;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchivoService implements IArchivoService {

	@Override
	public void cargar(String ruta, MultipartFile archivo) throws Exception {
		try {
			String test = new File("").getAbsolutePath();
			Path pathSinNombre = Paths.get(test +"/archivos" + ruta);
			Files.createDirectories(pathSinNombre);
			byte[] bytes = archivo.getBytes();
            Files.write(Paths.get(test+"/archivos"+ ruta + "/" + archivo.getOriginalFilename()), bytes);
	    } catch (Exception e) {
	      throw e;
	    }
	}

	@Override
	public Resource descargar(String ruta) throws Exception {
		try {
			String test = new File("").getAbsolutePath();
			InputStreamResource resource = new InputStreamResource(new FileInputStream(test+"/archivos"+ ruta));
			return resource;
		} catch (Exception e) {
			throw e;
		}
	}

}
