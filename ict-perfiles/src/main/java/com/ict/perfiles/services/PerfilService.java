package com.ict.perfiles.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.Column;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.Order;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.Beneficio;
import com.ict.commons.entity.Empresa;
import com.ict.commons.entity.LocalizacionInmueble;
import com.ict.commons.entity.Perfil;
import com.ict.commons.entity.PerfilView;
import com.ict.commons.entity.PerfilViewEM;
import com.ict.commons.entity.Perfiles;
import com.ict.commons.entity.PerfilesEmpresa;
import com.ict.commons.entity.Solicitud;
import com.ict.commons.models.ReporteGraficosModel;
import com.ict.perfiles.models.PerfilExcelModel;
import com.ict.perfiles.repositories.IBeneficioFeign;
import com.ict.perfiles.repositories.IEstablecimientosPorAnalistaRepository;
import com.ict.perfiles.repositories.ILocalizacionInmuebleRepository;
import com.ict.perfiles.repositories.IPerfilRepository;
import com.ict.perfiles.repositories.IPerfilViewEMRepository;
import com.ict.perfiles.repositories.IPerfilViewRepository;
import com.ict.perfiles.repositories.IPerfilesEmpresaRepository;
import com.ict.perfiles.repositories.IPerfilesRepository;
import com.ict.perfiles.repositories.ISolicitudFeign;

@Service
public class PerfilService implements IPerfilService {

	@Autowired
	private IPerfilRepository perfilRepository;

	@Autowired
	private ISolicitudFeign solicitudFeign;

	@Autowired
	private IBeneficioFeign beneficioFeign;

	@Autowired
	private ILocalizacionInmuebleRepository localizacionInmuebleRepository;

	@Autowired
	private IEmpresaService empresaRepository;

	@Autowired
	private IPerfilesRepository perfilesRepository;

	@Autowired
	private IEstablecimientosPorAnalistaRepository estabPorAnalisRepository;

	@Autowired
	private IPerfilViewRepository repoPerfilView;

	@Autowired
	private IPerfilViewEMRepository repoPerfilViewEM;
	
	@Autowired
	private IPerfilesEmpresaRepository perfilesEmpresaRepository;

	@Override
	public Perfil guardar(Perfil perfil) {
		Perfil existente = this.obtenerPorNombreUsuario(perfil.getUsuarioSistema().getNombreUsuario());
		if (existente != null) {
			if(perfil.getEmpresa() == null && existente.getEmpresa() != null) {
                perfil.setEmpresa(existente.getEmpresa());
            }
            else if(perfil.getPersonaFisica() == null && existente.getPersonaFisica() != null && perfil.getPersonaJuridica() == null) {
                perfil.setPersonaFisica(existente.getPersonaFisica());
            }
            else if(perfil.getPersonaJuridica() == null && existente.getPersonaJuridica() != null && perfil.getPersonaFisica() == null){
                perfil.setPersonaJuridica(existente.getPersonaJuridica());
            }
			
		}
		if (perfil.getId() == null) {
			perfil.setId(this.obtenerProxId());
		}
		if (perfil.getPersonaFisica() != null && perfil.getPersonaFisica().getId() == null) {
			perfil.getPersonaFisica().setId(perfil.getId());
		}
		if (perfil.getPersonaJuridica() != null && perfil.getPersonaJuridica().getId() == null) {
			perfil.getPersonaJuridica().setId(perfil.getId());
		}
		if (perfil.getEmpresa() != null) {
			if (perfil.getEmpresa().getId() == null || (existente != null && existente.getEmpresa() == null)) {
				perfil.getEmpresa().setAprobar(false);
				perfil.getEmpresa().setRechazar(false);
				perfil.getEmpresa().setDesafiliar(false);
				perfil.getEmpresa().setPendienteDesafiliar(false);
				perfil.getEmpresa().setId(perfil.getId());
			} else if (existente != null) {
				perfil.getEmpresa().setAprobar(existente.getEmpresa().getAprobar());
				perfil.getEmpresa().setRechazar(existente.getEmpresa().getRechazar());
				perfil.getEmpresa().setDesafiliar(existente.getEmpresa().getDesafiliar());
				perfil.getEmpresa().setPendienteDesafiliar(existente.getEmpresa().getPendienteDesafiliar());
				perfil.getEmpresa().setFechaAprobacion(existente.getEmpresa().getFechaAprobacion());
				perfil.getEmpresa().setFechaRechazo(existente.getEmpresa().getFechaRechazo());
				perfil.getEmpresa().setFechaDesafiliacion(existente.getEmpresa().getFechaDesafiliacion());
				perfil.getEmpresa().setMotivoRechazo(existente.getEmpresa().getMotivoRechazo());
				perfil.getEmpresa().setMotivoDesafiliacion(existente.getEmpresa().getMotivoDesafiliacion());
			}
		}
		/*
		 * if (perfil.getSolicitud() != null && !perfil.getSolicitud().isEmpty()) { Long
		 * id = this.solicitudFeign.obtenerProxId(); Long idB =
		 * this.beneficioFeign.obtenerProxId(); for (Solicitud ent :
		 * perfil.getSolicitud()) { if (ent.getId() == null) { ent.setId(id); id++; } if
		 * (ent.getBeneficios() != null && !ent.getBeneficios().isEmpty()) { for
		 * (Beneficio mod : ent.getBeneficios()) { if (mod.getId() == null) {
		 * mod.setId(idB); idB++; } } } } }
		 */
		Perfil nuevoPerfil = this.perfilRepository.save(perfil);
		return nuevoPerfil;
	}

	@Override
	public Perfil obtenerPorNombreUsuario(String nombreUsuario) {

		List<Perfil> resultado = this.perfilRepository.findByUsuarioSistema_NombreUsuario(nombreUsuario);

		if (resultado.isEmpty()) {
			return null;
		}

		return resultado.get(0);
	}

	@Override
	public Perfil obtenerPorIdentificación(Perfil perfil) {
		List<Perfil> resultado = new ArrayList<Perfil>();
		if (perfil.getPersonaFisica() != null) {
			resultado = this.perfilRepository
					.findByPersonaFisica_Identificacion(perfil.getPersonaFisica().getIdentificacion());
		}
		if (perfil.getPersonaJuridica() != null) {
			resultado = this.perfilRepository
					.findByPersonaJuridica_CedJuridico(perfil.getPersonaJuridica().getCedJuridico());
		}
		return !resultado.isEmpty() ? resultado.get(0) : null;
	}

	private Long obtenerProxId() {
		Perfil entidad = this.perfilRepository.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}

	@Override
	public Perfil obtenerPorId(Long idPerfil) {
		return this.perfilRepository.findById(idPerfil).get();
	}

	@Override
	public List<PerfilExcelModel> obtenerListaPerfilesExcel() {
		List<Perfiles> perfiles = this.perfilesRepository
				.findByAprobarInmuebleTrueAndDesafiliarInmuebleFalseAndRechazarInmuebleFalse();
		List<PerfilExcelModel> respuesta = new ArrayList<PerfilExcelModel>();

		respuesta.add(agregarEncabezados());

		for (Perfiles perfil : perfiles) {
			if (perfil != null) {
				PerfilExcelModel perfilExcel = new PerfilExcelModel();
				SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
				perfilExcel.setTipo1("Hospedaje no tradicional");
				perfilExcel.setTipo2(perfil.getCodigoTipoServicio().equalsIgnoreCase("HNT") ? "Hospedaje no tradicional"
						: "Empresa intermediaria o comercializadora");
				perfilExcel.setCedula(perfil.getIdPersonaFisica() != null ? perfil.getIdentificacionFisica()
						: perfil.getIdPersonaJuridica() != null ? perfil.getCedJuridico() : "");
				perfilExcel.setNombre1(this.verificarNombre1(perfil));
				perfilExcel.setNombre2(this.verificarNombre2(perfil));
				perfilExcel.setIdentificacionRepresentante(perfil.getIdenRepresentante() != null ? perfil.getIdenRepresentante() : "");
				perfilExcel.setDomicilioLegal(perfil.getIdPersonaFisica() != null ? perfil.getDomicilioLegal()
						: perfil.getIdPersonaJuridica() != null ? perfil.getDomicilioLegalRepresentante() : "");
				perfilExcel.setEstado("En operación (Activo)");
				perfilExcel.setCodigo(perfil.getId().getCodigoInmueble().equalsIgnoreCase("0") ? ""
						: perfil.getId().getCodigoInmueble());
				perfilExcel
						.setFechaAprobacion(perfil.getFechaAprobacionInmueble() != null
										? formateadorFecha.format(perfil.getFechaAprobacionInmueble()) : "");
				perfilExcel.setProvincia(perfil.getProvincia());
				perfilExcel.setCanton(perfil.getCanton());
				perfilExcel.setDistrito(perfil.getDistrito());
				perfilExcel.setDireccion(perfil.getDireccionInmueble());
				perfilExcel.setNumeroHabitaciones(perfil.getNumHabitaciones() != null ? String.valueOf(perfil.getNumHabitaciones()) : "0");
				perfilExcel.setNumeroHuespedes(perfil.getNumHuespedes() != null ? String.valueOf(perfil.getNumHuespedes()) : "0");
				perfilExcel.setCorreo(perfil.getId().getCorreo());
				perfilExcel.setTelefono(perfil.getTelefonoInmueble());
				perfilExcel.setCorreoNotificaciones(
						perfil.getCorreoInmueble() != null ? perfil.getCorreoInmueble() : "");
				perfilExcel.setUrlEmpresa(perfil.getUrlInmueble() != null? perfil.getUrlInmueble() : "");
				respuesta.add(perfilExcel);
			}
		}
		return respuesta;
	}

	private PerfilExcelModel agregarEncabezados() {
		PerfilExcelModel encabezado = new PerfilExcelModel();
		encabezado.setTipo1("CLASIFICACIÓN ICT");
		encabezado.setTipo2("ACTIVIDAD");
		encabezado.setCedula("CÉDULA");
		encabezado.setNombre1("NOMBRE O RAZÓN SOCIAL");
		encabezado.setNombre2("NOMBRE DEL REPRESENTANTE LEGAL");
		encabezado.setIdentificacionRepresentante("IDENTIFICACIÓN DEL REPRESENTANTE LEGAL");
		encabezado.setDomicilioLegal("DOMICILIO LEGAL");
		encabezado.setEstado("ESTADO");
		encabezado.setCodigo("NO DE RESOLUCIÓN");
		encabezado.setFechaAprobacion("FECHA DE OTORGAMIENTO");
		encabezado.setProvincia("PROVINCIA");
		encabezado.setCanton("CANTÓN");
		encabezado.setDistrito("DISTRITO");
		encabezado.setDireccion("DIRECCIÓN");
		encabezado.setNumeroHabitaciones("NÚMERO DE HABITACIONES");
		encabezado.setNumeroHuespedes("NÚMERO MÁXIMO DE HUÉSPEDES");
		encabezado.setCorreo("CORREO(S)");
		encabezado.setTelefono("TELÉFONO(S)");
		encabezado.setCorreoNotificaciones("CORREO DE NOTIFICACIÓN");
		encabezado.setUrlEmpresa("PÁGINA WEB");
		return encabezado;
	}

	public String verificarNombre1(Perfiles perfil) {
		String formatoNombre = "%s %s %s";
		if (perfil.getCodigoTipoServicio() != null) {
			if (perfil.getCodigoTipoServicio().equalsIgnoreCase("HNT")) {
				if (perfil.getIdPersonaFisica() != null) {
					return String.format(formatoNombre, perfil.getApellido1(), perfil.getApellido2(),
							perfil.getNombre());
				} else if (perfil.getIdPersonaJuridica() != null) {
					return perfil.getNombreComercial();
				}
			}
		}
		return "";
	}

	public String verificarNombre2(Perfiles perfil) {
		String formatoNombre = "%s %s %s";
		if (perfil.getCodigoTipoServicio() != null) {
			if (perfil.getCodigoTipoServicio().equalsIgnoreCase("HNT")) {
				if (perfil.getIdPersonaJuridica() != null) {
					return String.format(formatoNombre, perfil.getApellido1Representante(),
							perfil.getApellido2Representante(), perfil.getNombreRepresentante());
				}
			}
		}
		return "";
	}

	@Override
	public List<PerfilExcelModel> obtenerListaPerfilesExcelEmpresa() {
		List<PerfilesEmpresa> perfiles = this.perfilesEmpresaRepository
				.findByAprobarEmpresaTrueAndDesafiliarEmpresaFalseAndRechazarEmpresaFalse();
		List<PerfilExcelModel> respuesta = new ArrayList<PerfilExcelModel>();

		respuesta.add(agregarEncabezadosEmpresa());

		for (PerfilesEmpresa perfil : perfiles) {
			if (perfil != null) {
				PerfilExcelModel perfilExcel = new PerfilExcelModel();
				Empresa oEmpresa = perfil.getIdEmpresa() != null
						? empresaRepository.obtenerEmpresaPorId(perfil.getIdEmpresa())
						: new Empresa();
				SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
				perfilExcel.setTipo1("Hospedaje no tradicional");
				perfilExcel.setTipo2(perfil.getCodigoTipoServicio().equalsIgnoreCase("EI")
						? "Empresa intermediaria o comercializadora"
						: "");
				perfilExcel.setCedulaJuridica(perfil.getCedulaJuridica() != null ? perfil.getCedulaJuridica() : "");
				perfilExcel.setNombre2(this.verificarNombre2Empresa(perfil));
				perfilExcel.setNombre1(this.verificarNombre1Empresa(perfil));
				perfilExcel.setCedula(perfil.getIdEmpresa() != null ? oEmpresa.getIdentificacion() : "");
				perfilExcel.setDireccion(perfil.getDireccion());
				perfilExcel.setProvincia(perfil.getProvincia());
				perfilExcel.setCanton(perfil.getCanton());
				perfilExcel.setDistrito(perfil.getDistrito());
				perfilExcel.setCorreoNotificaciones(perfil.getId().getCodigoInmueble().equalsIgnoreCase("0")
						? (perfil.getCorreoNotificaciones() != null ? perfil.getCorreoNotificaciones() : "")
						: "");
				perfilExcel.setTelefonoEmpresa(perfil.getTelefonoEmpresa() != null ? perfil.getTelefonoEmpresa() : "");
				perfilExcel.setUrlEmpresa(perfil.getId().getCodigoInmueble().equalsIgnoreCase("0")
						? (perfil.getUrlEmpresa() != null ? perfil.getUrlEmpresa() : "")
						: "");
				perfilExcel.setEstado("En operación (Activo)");
				perfilExcel
						.setFechaAprobacion(perfil.getId().getCodigoInmueble().equalsIgnoreCase("0")
								? (perfil.getFechaAprobacionEmpresa() != null
										? formateadorFecha.format(perfil.getFechaAprobacionEmpresa())
										: "")
								: "");
				perfilExcel.setCorreo(perfil.getId().getCorreo());
				perfilExcel.setIdentificadorFisicoJuridico(
						perfil.getIdentificadorFisicoJuridico() != null ? perfil.getIdentificadorFisicoJuridico() : "");
				respuesta.add(perfilExcel);
			}
		}
		return respuesta;
	}

	private PerfilExcelModel agregarEncabezadosEmpresa() {
		PerfilExcelModel encabezado = new PerfilExcelModel();
		encabezado.setTipo1("CLASIFICACIÓN ICT");
		encabezado.setTipo2("ACTIVIDAD");
		encabezado.setCedulaJuridica("CEDULA JURIDÍCA");
		encabezado.setNombre2("RAZÓN SOCIAL");
		encabezado.setNombre1("NOMBRE");
		encabezado.setCedula("IDENTIFICACIÓN FISICA");
		encabezado.setDireccion("DOMICILIO LEGAL");
		encabezado.setProvincia("PROVINCIA");
		encabezado.setCanton("CANTÓN");
		encabezado.setDistrito("DISTRITO");
		encabezado.setCorreoNotificaciones("CORREO DE NOTIFICACIÓN");
		encabezado.setTelefonoEmpresa("TELÉFONO DE EMPRESA");
		encabezado.setUrlEmpresa("PÁGINA WEB");
		encabezado.setEstado("ESTADO");
		encabezado.setFechaAprobacion("FECHA DE OTORGAMIENTO");
		encabezado.setCorreo("CORREO(S)");
		encabezado.setIdentificadorFisicoJuridico("IDENTIFICADOR FISICA O JURIDICA");
		return encabezado;
	}

	public String verificarNombre1Empresa(PerfilesEmpresa perfil) {
		String formatoNombre = "%s %s %s";
		if (perfil.getCodigoTipoServicio() != null) {
			if (perfil.getCodigoTipoServicio().equalsIgnoreCase("EI")) {
				return String.format(formatoNombre, perfil.getPrimerApeEmpresa(), perfil.getSegundoApeEmpresa(),
						perfil.getNombreRepresentanteEmpresa());
			}
		}
		return "";
	}

	public String verificarNombre2Empresa(PerfilesEmpresa perfil) {
		if (perfil.getCodigoTipoServicio() != null) {
			if (perfil.getCodigoTipoServicio().equalsIgnoreCase("EI")) {
				return perfil.getRazonSocialEmpresa();
			}
		}
		return "";
	}

	@Override
	public List<ReporteGraficosModel> obtenerGraficoTipoServicio() {
		List<ReporteGraficosModel> reporte = new ArrayList<ReporteGraficosModel>();
		reporte = this.perfilRepository.obtenerDatosGraficoTipoServicio();
		return reporte;
	}

	@Override
	public Long obtenerCantidadPerfiles() {
		return this.perfilRepository.count();
	}

	private DataTablesInput corregirOrdenamiento(DataTablesInput input) {
		int orderPos = input.getColumns().size();
		Column column = new Column();
		column.setData("id");
		column.setOrderable(true);
		column.setSearchable(false);
		input.getColumns().add(column);
		//
		Order order = new Order();
		order.setColumn(orderPos);
		order.setDir("desc");
		input.getOrder().add(order);
		return input;
	}

	@Override
	public DataTablesOutput<PerfilView> getTablaEstablecimientosAprobados(DataTablesInput input) {
		return this.repoPerfilView.findAll(corregirOrdenamiento(input),
				(root, query, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.equal(root.get("aprobado"), true),
						criteriaBuilder.equal(root.get("rechazado"), false),
						criteriaBuilder.equal(root.get("desafiliado"), false)));
	}

	@Override
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPorUsuario(DataTablesInput input, Long idUsuario) {
		if (idUsuario != null) {
			return this.repoPerfilView.findAll(corregirOrdenamiento(input),
					(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("analista"), idUsuario));
		}
		return this.repoPerfilView.findAll(corregirOrdenamiento(input));
	}

	@Override
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPendientesAprobar(DataTablesInput input) {
		return this.repoPerfilView.findAll(corregirOrdenamiento(input),
				(root, query, criteriaBuilder) -> criteriaBuilder.and(
						criteriaBuilder.equal(root.get("aprobado"), false),
						criteriaBuilder.equal(root.get("rechazado"), false),
						criteriaBuilder.equal(root.get("desafiliado"), false)));
	}

	@Override
	public DataTablesOutput<PerfilView> getTablaEstablecimientosPendientesDesafiliar(DataTablesInput input) {
		return this.repoPerfilView.findAll(corregirOrdenamiento(input),
				(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("pendienteDesafiliar"), true));
	}

	@Override
	public DataTablesOutput<PerfilViewEM> getTablaEstablecimientosEmAprobados(DataTablesInput input) {
		return this.repoPerfilViewEM.findAll(corregirOrdenamiento(input),
				(root, query, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.equal(root.get("aprobado"), true),
						criteriaBuilder.equal(root.get("rechazado"), false),
						criteriaBuilder.equal(root.get("desafiliado"), false)));
	}
}
