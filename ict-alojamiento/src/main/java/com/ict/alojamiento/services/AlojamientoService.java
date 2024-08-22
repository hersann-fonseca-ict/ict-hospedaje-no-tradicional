package com.ict.alojamiento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.alojamiento.models.AlojamientoModel;
import com.ict.alojamiento.repositories.IAlojamientoRepository;
import com.ict.commons.entity.Alojamiento;
import com.ict.commons.models.ReporteGraficosModel;

@Service
public class AlojamientoService implements IAlojamientoService {

	@Autowired
	private IAlojamientoRepository repository;

	@Override
	public Alojamiento guardar(Alojamiento alojamiento) {

		if (alojamiento.getId() == null) {
			alojamiento.setId(this.obtenerProxId());
		}
		Alojamiento nuevo = this.repository.save(alojamiento);
		return nuevo;
	}

	private Long obtenerProxId() {
		Alojamiento entidad = this.repository.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}

	@Override
	public Alojamiento obtenerPorNombre(String nombre) {
		return this.repository.findByNombre(nombre);
	}

	@Override
	public List<Alojamiento> listado() {
		return this.repository.findAll();
	}

	@Override
	public List<Alojamiento> listadoPorPadre(Long padre) {
		List<Alojamiento> lista = this.repository.findByPadreOrderByNivelAsc(padre);
		return lista;
	}

	@Override
	public List<AlojamientoModel> listadoClase() {
		List<AlojamientoModel> lista = new ArrayList<>();

		for (Alojamiento todos : this.repository.obtenerPadres()) {
			AlojamientoModel modelo = new AlojamientoModel();
			modelo.setName(todos);
			modelo.getChildren().addAll(this.obtenerHijos(todos.getId()));
			lista.add(modelo);
		}
		return lista;
	}

	private List<AlojamientoModel> obtenerHijos(Long id) {
		List<AlojamientoModel> lista = new ArrayList<>();
		for (Alojamiento hijos : this.repository.findByPadreOrderByNivelAsc(id)) {
			AlojamientoModel hijo = new AlojamientoModel();
			hijo.setName(hijos);  
			hijo.getChildren().addAll(obtenerHijos(hijos.getId()));
			lista.add(hijo);
		}
		return lista;
	}

	@Override
	public List<ReporteGraficosModel> obtenerDatosGrafico() {
		List<ReporteGraficosModel> reporte = new ArrayList<ReporteGraficosModel>();
		reporte = this.repository.obtenerDatosGraficoAlojamiento();
		return reporte;
	}
	 
}
