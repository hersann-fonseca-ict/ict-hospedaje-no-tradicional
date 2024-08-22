package com.ict.perfiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.commons.entity.PerfilesEmpresa;
import java.util.List;

public interface IPerfilesEmpresaRepository extends JpaRepository<PerfilesEmpresa, Long> {

	List<PerfilesEmpresa> findByAprobarEmpresaTrueAndDesafiliarEmpresaFalseAndRechazarEmpresaFalse();
}
