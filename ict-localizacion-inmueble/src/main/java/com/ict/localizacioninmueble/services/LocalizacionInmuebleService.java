package com.ict.localizacioninmueble.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.LocalizacionInmueble;
import com.ict.commons.entity.Secuencia;
import com.ict.localizacioninmueble.repositories.ILocalizacionInmueble;
import com.ict.localizacioninmueble.repositories.ISecuenciaFeign;

@Service
public class LocalizacionInmuebleService implements ILocalizacionInmuebleService {

	@Autowired
	private ILocalizacionInmueble repository;

	@Autowired
	private ISecuenciaFeign secuenciaFeign;

	private Long obtenerProxId() {
		LocalizacionInmueble entidad = this.repository.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}

	@Override
	public Long guardarLista(List<LocalizacionInmueble> lista) {
		Long id = this.obtenerProxId();
		Long idPerfil = 0L;
		if (!lista.isEmpty()) {
			idPerfil = lista.get(0).getPerfil().getId();
		}
		Map<Long, LocalizacionInmueble> listaExistente = this.obtenerLista(idPerfil).stream()
				.collect(Collectors.toMap(LocalizacionInmueble::getId, Function.identity()));
		for (LocalizacionInmueble entidad : lista) {
			if (entidad.getId() == null) {
				entidad.setId(id);
				entidad.setAprobar(false);
				entidad.setRechazar(false);
				entidad.setDesafiliar(false);
				entidad.setPendienteDesafiliar(false);
				id++;
			} else if (listaExistente.get(entidad.getId()) != null) {
				LocalizacionInmueble existente = listaExistente.get(entidad.getId());
				entidad.setAprobar(existente.getAprobar());
				entidad.setRechazar(existente.getRechazar());
				entidad.setDesafiliar(existente.getDesafiliar());
				entidad.setPendienteDesafiliar(existente.getPendienteDesafiliar());
				entidad.setFechaAprobacion(existente.getFechaAprobacion());
				entidad.setFechaRechazo(existente.getFechaRechazo());
				entidad.setFechaDesafiliacion(existente.getFechaDesafiliacion());
				entidad.setMotivoRechazo(existente.getMotivoRechazo());
				entidad.setMotivoDesafiliacion(existente.getMotivoDesafiliacion());
			}
			if (entidad.getCodigo() == null || entidad.getCodigo().isEmpty()) {
				String codigoTipoServicioLocalizaciones = "HNT";
                entidad.setCodigo(this.secuenciaFeign
                        .obtenerProxSec(new Secuencia(codigoTipoServicioLocalizaciones, null)));
			}
			entidad.setEditable(false);
			this.repository.save(entidad);
		}
		return idPerfil;
	}

	@Override
	public void eliminarLista(List<LocalizacionInmueble> lista) {
		this.repository.deleteAll(lista);
	}

	@Override
	public LocalizacionInmueble guardar(LocalizacionInmueble entidad) {
		if (entidad.getId() == null) {
			entidad.setId(this.obtenerProxId());
		}
		LocalizacionInmueble nuevoUsuario = this.repository.save(entidad);
		return nuevoUsuario;
	}

	@Override
	public void eliminar(Long id) {
		LocalizacionInmueble existe = this.repository.findById(id).get();
		if (existe != null) {
			existe.setAlojamiento(null);
			existe.setPerfil(null);
			this.repository.save(existe);

			this.repository.deleteById(id);
		}
	}

	@Override
	public List<LocalizacionInmueble> obtenerLista(Long idPerfil) {
		return this.repository.findByPerfil_Id(idPerfil);
	}

	@Override
	public Integer cantidadPerfilesConInmuebles() {
		List<Long> listaPerfiles = this.repository.listaPerfilesConInmuebles();
		if (listaPerfiles.isEmpty()) {
			return 0;
		} else {
			return listaPerfiles.size();
		}
	}

	/**
	 * Obtener inmueble por ID
	 */
	@Override
	public LocalizacionInmueble obtenerInmuebleById(Long id) {
		Optional<LocalizacionInmueble> respuesta = this.repository.findById(id);
		LocalizacionInmueble inmueble = respuesta.get();
		return inmueble;
	}
}
