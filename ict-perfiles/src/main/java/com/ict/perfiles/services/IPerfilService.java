package com.ict.perfiles.services;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.ict.commons.entity.Perfil;
import com.ict.commons.entity.PerfilView;
import com.ict.commons.entity.PerfilViewEM;
import com.ict.commons.models.ReporteGraficosModel;
import com.ict.perfiles.models.PerfilExcelModel;

public interface IPerfilService {

	public Perfil guardar(Perfil perfil);
	
	public Perfil obtenerPorNombreUsuario(String nombreUsuario);
	public Perfil obtenerPorIdentificación(Perfil perfil);
	public Perfil obtenerPorId(Long idPerfil);
	public List<PerfilExcelModel> obtenerListaPerfilesExcel();
	public List<PerfilExcelModel> obtenerListaPerfilesExcelEmpresa();
	
	public List<ReporteGraficosModel> obtenerGraficoTipoServicio();
	public Long obtenerCantidadPerfiles();
	public DataTablesOutput<PerfilView> getTablaEstablecimientosAprobados(DataTablesInput input);
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPorUsuario(DataTablesInput input, Long idUsuario);
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPendientesAprobar(DataTablesInput input);
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPendientesDesafiliar(DataTablesInput input);
    public DataTablesOutput<PerfilViewEM> getTablaEstablecimientosEmAprobados(DataTablesInput input);
}
