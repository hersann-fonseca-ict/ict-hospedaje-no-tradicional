package com.ict.tiposervicios.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.TipoServicio;
import com.ict.tiposervicios.repositories.ITipoServicioRepository;

@Service
public class TipoServicioService implements ITipoServicioService {

	@Autowired
	private ITipoServicioRepository tipoServicioRepository;

	@Override
	public TipoServicio guardar(TipoServicio tipoServicio) {
		tipoServicio.setFechaCreacion(new Date());
		if (tipoServicio.getId() == null) {
			tipoServicio.setId(this.obtenerProxId());
		}
		return this.tipoServicioRepository.save(tipoServicio);
	}

	@Override
	public TipoServicio obtenerPorId(Long id) {
		var valor = this.tipoServicioRepository.findById(id);
		if (valor != null) {
			return valor.get();
		}
		return null;
	}

	@Override
	public List<TipoServicio> obtener() {
		return this.tipoServicioRepository.findAll();
	}

	private Long obtenerProxId() {
		TipoServicio entidad = this.tipoServicioRepository.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}
}
