package com.ict.direccion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ict.commons.entity.Canton; 

@Repository
public interface ICantonRepository extends JpaRepository<Canton, Long> {
	public List<Canton> findByIdProvincia(Long idProvincia);
	
}
