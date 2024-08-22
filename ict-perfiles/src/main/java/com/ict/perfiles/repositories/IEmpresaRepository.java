package com.ict.perfiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.commons.entity.Empresa;


public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
	Empresa findFirstByOrderByIdDesc();
	
}
