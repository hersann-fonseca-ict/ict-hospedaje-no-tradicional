package com.ict.perfiles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ict.commons.entity.EstablecimientosPorAnalista;
import com.ict.commons.entity.UsuarioSistema;

public interface IEstablecimientosPorAnalistaRepository extends JpaRepository<EstablecimientosPorAnalista, Long> {
	static final String query = "SELECT epa FROM EstablecimientosPorAnalista epa " + "WHERE EXISTS ( "
			+ "	SELECT l.id " + "	FROM LocalizacionInmueble l "
			+ "	WHERE l.id = epa.localizacion.id AND l.perfil.id = :idPerfil " + ") OR EXISTS ( " + "	SELECT e.id "
			+ "	FROM Empresa e " + "	WHERE e.id = epa.empresa.id AND e.id = :idPerfil)";

	List<EstablecimientosPorAnalista> findByUsuarioSistema(UsuarioSistema usuarioSistema);

	@Query(value = query)
	List<EstablecimientosPorAnalista> findByPerfil(@Param("idPerfil") Long idPerfil);

	EstablecimientosPorAnalista findByUsuarioSistemaIdAndLocalizacionId(Long usuarioSistemaId, Long localizacionInmuebleId);
	
	EstablecimientosPorAnalista findByUsuarioSistemaIdAndEmpresaId(Long usuarioSistemaId, Long empresaId);
}
