package com.ict.perfiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.commons.entity.PersonaJuridica;


public interface IPersonaJuridicaRepository extends JpaRepository<PersonaJuridica, Long> {
	PersonaJuridica findFirstByOrderByIdDesc();
}
