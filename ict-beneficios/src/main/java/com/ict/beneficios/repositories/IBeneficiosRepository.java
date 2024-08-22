package com.ict.beneficios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Beneficio;

@Repository
public interface IBeneficiosRepository extends JpaRepository<Beneficio, Long> {
	List<Beneficio> findByNombreBeneficio(String nombreBeneficio);

	Beneficio findFirstByOrderByIdDesc();

}
