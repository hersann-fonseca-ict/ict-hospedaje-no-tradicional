package com.ict.direccion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.ict.commons.entity.Distrito;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Long> {
	public List<Distrito> findByIdCanton(Long idCanton);
	
}
