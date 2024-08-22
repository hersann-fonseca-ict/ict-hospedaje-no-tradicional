package com.ict.solicitudes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Solicitud;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitud, Long> {
	@Query(value="SELECT * FROM solicitud  WHERE solicitud.id_solicitud = ?1", nativeQuery = true)
    public Solicitud buscarPorId(Long id);
	
	Solicitud findFirstByOrderByIdDesc();

}
