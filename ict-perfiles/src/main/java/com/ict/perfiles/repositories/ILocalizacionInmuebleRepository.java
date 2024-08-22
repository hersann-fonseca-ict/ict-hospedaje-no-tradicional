package com.ict.perfiles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.commons.entity.LocalizacionInmueble;


public interface ILocalizacionInmuebleRepository extends JpaRepository<LocalizacionInmueble, Long> {
	List<LocalizacionInmueble> findByPerfilId(Long idPerfil);
}
