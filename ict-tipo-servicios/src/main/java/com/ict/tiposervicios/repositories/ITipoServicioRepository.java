package com.ict.tiposervicios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.TipoServicio;

@Repository
public interface ITipoServicioRepository extends JpaRepository<TipoServicio, Long> {
	TipoServicio findFirstByOrderByIdDesc();
}
