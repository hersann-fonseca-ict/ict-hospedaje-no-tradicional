package com.ict.perfiles.repositories;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Perfiles;

@Repository
public interface IPerfilesRepositoryTabla extends DataTablesRepository<Perfiles, Long>{

	List<Perfiles> findAllByAprobarInmuebleTrueAndDesafiliarInmuebleFalse();
}
