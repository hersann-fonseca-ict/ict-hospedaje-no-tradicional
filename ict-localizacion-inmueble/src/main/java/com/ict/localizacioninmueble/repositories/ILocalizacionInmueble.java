package com.ict.localizacioninmueble.repositories;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.ict.commons.entity.LocalizacionInmueble;

@Repository
public interface ILocalizacionInmueble extends JpaRepository<LocalizacionInmueble, Long> {
 	
	LocalizacionInmueble findFirstByOrderByIdDesc();
	
	List<LocalizacionInmueble> findByPerfil_Id(Long id);
	
	@Query(value = "SELECT DISTINCT l.perfil.id FROM LocalizacionInmueble l LEFT JOIN Perfil p ON p.id = l.perfil.id")
	List<Long> listaPerfilesConInmuebles();

}
