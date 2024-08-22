package com.ict.alojamiento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Alojamiento;
import com.ict.commons.models.ReporteGraficosModel;

@Repository
public interface IAlojamientoRepository extends JpaRepository<Alojamiento, Long> {

	List<Alojamiento> findByPadreOrderByNivelAsc(Long padre);

	Alojamiento findByNombre(String nombre);

	Alojamiento findFirstByOrderByIdDesc();
	
	@Query(value = "SELECT alo FROM Alojamiento alo WHERE padre IS NULL" , nativeQuery = false)
	List<Alojamiento> obtenerPadres();
 
	@Query(value = "SELECT new com.ict.commons.models.ReporteGraficosModel(a.nombre as nombre, COUNT(l.id) as valor) from Alojamiento a LEFT OUTER JOIN LocalizacionInmueble l ON l.alojamiento.id = a.id GROUP BY a.nombre")
	List<ReporteGraficosModel> obtenerDatosGraficoAlojamiento();
	
}
