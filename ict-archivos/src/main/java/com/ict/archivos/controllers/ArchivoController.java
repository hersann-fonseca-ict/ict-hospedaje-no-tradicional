package com.ict.archivos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.InputStreamResource;
import com.ict.archivos.services.IArchivoService;


@RestController
public class ArchivoController {

	@Autowired
	private IArchivoService archivoService;
	
	@PostMapping("/subir")
	  public ResponseEntity<Boolean> subir(@RequestParam("ruta") String ruta, 
			  @RequestParam("archivo") MultipartFile archivo) {
	    try {
	    	this.archivoService.cargar(ruta, archivo);
	    	return ResponseEntity.status(HttpStatus.OK).body(true);
	    } catch (Exception e) {
	    	System.err.println(e.getMessage());
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	  }
	
	@GetMapping(path = "/bajar")
	public ResponseEntity<Resource> bajar(String ruta, HttpServletRequest request) {
		try {
			
			InputStreamResource resource = new InputStreamResource(new FileInputStream("archivos" + ruta)); 
		
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (Exception ex) {
				// logger.info("Could not determine file type.");
			}

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = Files.probeContentType(Path.of(ruta));
			}
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + Path.of(ruta).getFileName() + "\"")
					.body(resource);
	        
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		 
	}
	@GetMapping(path = "/listar")
	public Set<String> listFilesUsingDirectoryStream(String dir) throws IOException {
	    Set<String> fileList = new HashSet<>();
	    String test = new File("").getAbsolutePath();
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(test+'/'+dir))) {
	        for (Path path : stream) {
	            if (!Files.isDirectory(path)) {
	                fileList.add(path.getFileName()
	                    .toString());
	            }
	        }
	    }
	    return fileList;
	}	
}
