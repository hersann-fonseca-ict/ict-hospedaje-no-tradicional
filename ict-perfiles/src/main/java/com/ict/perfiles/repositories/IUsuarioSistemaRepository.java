package com.ict.perfiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.commons.entity.UsuarioSistema;


public interface IUsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long> {

	UsuarioSistema findFirstByOrderByIdDesc();
}
