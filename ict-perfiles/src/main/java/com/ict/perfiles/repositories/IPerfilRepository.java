package com.ict.perfiles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Perfil;
import com.ict.commons.models.ReporteGraficosModel;


@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Long> {

	public List<Perfil> findByUsuarioSistema_NombreUsuario(final String nombreUsuario);

	public List<Perfil> findByPersonaFisica_Id(final Long id);

	public List<Perfil> findByPersonaJuridica_Id(final Long id);

	public List<Perfil> findByPersonaFisica_Identificacion(final String identificacion);

	public List<Perfil> findByPersonaJuridica_CedJuridico(final String cedJuridico);

	Perfil findFirstByOrderByIdDesc();
	
	@Query(value = "SELECT new com.ict.commons.models.ReporteGraficosModel(p.codigoTipoServicio as nombre, COUNT(p.id) as valor) from Perfil p GROUP BY p.codigoTipoServicio")
	List<ReporteGraficosModel> obtenerDatosGraficoTipoServicio();

}
