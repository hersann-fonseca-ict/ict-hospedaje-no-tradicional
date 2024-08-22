package com.ict.perfiles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Perfiles;

@Repository
public interface IPerfilesRepository extends JpaRepository<Perfiles, Long>{

	List<Perfiles> findByAprobarInmuebleTrueAndDesafiliarInmuebleFalse();
	List<Perfiles> findByAprobarInmuebleTrueAndDesafiliarInmuebleFalseAndRechazarInmuebleFalse();
}
