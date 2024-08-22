package com.ict.perfiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.commons.entity.PersonaFisica;


public interface IPersonaFisicaRepository extends JpaRepository<PersonaFisica, Long> {
	PersonaFisica findFirstByOrderByIdDesc();
}
