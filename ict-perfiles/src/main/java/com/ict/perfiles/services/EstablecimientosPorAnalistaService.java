package com.ict.perfiles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.EstablecimientosPorAnalista;
import com.ict.commons.entity.UsuarioSistema;
import com.ict.perfiles.repositories.IEstablecimientosPorAnalistaRepository;

@Service
public class EstablecimientosPorAnalistaService implements IEstablecimientosPorAnalistaService {

	@Autowired
	private IEstablecimientosPorAnalistaRepository repository;

	@Override
	public List<EstablecimientosPorAnalista> obtenerEstablecimientosPorAnalista(UsuarioSistema usuario) {
		return this.repository.findByUsuarioSistema(usuario);
	}

	@Override
	public EstablecimientosPorAnalista asignarEstablecimientoPorAnalista(EstablecimientosPorAnalista establecimiento) {
		return this.repository.save(establecimiento);
	}

	@Override
	public EstablecimientosPorAnalista obtieneEstablecimiento(EstablecimientosPorAnalista establecimiento) {
		if (establecimiento.getLocalizacion() != null) {
			return this.repository.findByUsuarioSistemaIdAndLocalizacionId(establecimiento.getUsuarioSistema().getId(),
					establecimiento.getLocalizacion().getId());
		} else {
			return this.repository.findByUsuarioSistemaIdAndEmpresaId(establecimiento.getUsuarioSistema().getId(),
					establecimiento.getEmpresa().getId());
		}
	}

	@Override
	public boolean quitarAsignacionEstablecimientoPorAnalista(EstablecimientosPorAnalista establecimiento) {
		this.repository.delete(establecimiento);
		return true;
	}

}
