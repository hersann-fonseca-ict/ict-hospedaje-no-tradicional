package com.ict.localizacioninmueble.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ict.commons.entity.Provincia;
import com.ict.commons.models.ReporteGraficosModel;

public interface IProvinciaRepository extends JpaRepository<Provincia, Long> {

	@Query(value = "SELECT new com.ict.commons.models.ReporteGraficosModel(p.nombre as nombre, COUNT(l.id) as valor) from Provincia p LEFT OUTER JOIN LocalizacionInmueble l ON l.provincia = p.id GROUP BY p.nombre")
	List<ReporteGraficosModel> obtenerDatos();
	
}
