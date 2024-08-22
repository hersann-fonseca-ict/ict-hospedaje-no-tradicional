package com.ict.direccion.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.Canton;
import com.ict.commons.entity.Distrito;
import com.ict.commons.entity.Provincia;
import com.ict.direccion.repositories.ICantonRepository;
import com.ict.direccion.repositories.IDistritoRepository;
import com.ict.direccion.repositories.IProvinciaRepository;

@Service
public class DireccionService implements IDireccionService {

	@Autowired
	private IProvinciaRepository direccionProvinciaRepository;
	@Autowired
	private ICantonRepository direccionCantonRepository;
	@Autowired
	private IDistritoRepository direccionDistritoRepository;

	@Override
	public Provincia obtenerProvinciaPorId(Long id) {
		var valor = this.direccionProvinciaRepository.findById(id);
		if (valor != null) {
			return valor.get();
		}
		return null;
	}

	@Override
	public List<Provincia> obtenerProvincias() {
		return this.direccionProvinciaRepository.findAll();
	}

	@Override
	public List<Canton> obtenerCantonPorId(Long id) {
		return this.direccionCantonRepository.findByIdProvincia(id);

	}

	@Override
	public List<Canton> obtenerCantones() {
		return this.direccionCantonRepository.findAll();
	}

	@Override
	public List<Distrito> obtenerDistritosPorId(Long id) {
		return this.direccionDistritoRepository.findByIdCanton(id);

	}

	@Override
	public List<Distrito> obtenerDistritos() {
		return this.direccionDistritoRepository.findAll();
	}

	@Override
	public String obtenerCantonNombre(Long id) {
		return this.direccionCantonRepository.findById(id).get().getNombre();
	}

	@Override
	public String obtenerDistritoNombre(Long id) {
		return this.direccionDistritoRepository.findById(id).get().getNombre();
	}

}
