package com.ict.solicitudes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ict.commons.entity.Beneficio;
import com.ict.commons.entity.Solicitud;
import com.ict.solicitudes.models.Pagina;
import com.ict.solicitudes.models.SolicitudesPaginados;
import com.ict.solicitudes.repositories.IBeneficioFeign;
import com.ict.solicitudes.repositories.ISolicitudRepository;

@Service
public class SolicitudService implements ISolicitudService {

	@Autowired
	private ISolicitudRepository repositorio;
	@Autowired
	private IBeneficioFeign beneficioFeign;

	@Override
	public Solicitud guardar(Solicitud solicitud) {
		if (solicitud.getId() == null) {
			solicitud.setId(this.obtenerProxId());
		}
		if (solicitud.getBeneficios() != null && !solicitud.getBeneficios().isEmpty()) {
			Long id = this.beneficioFeign.obtenerProxId();
			for (Beneficio mod : solicitud.getBeneficios()) {
				if (mod.getId() == null) {
					mod.setId(id);
					id++;
				}
			}
		}
		return this.repositorio.save(solicitud);
	}

	@Override
	public Solicitud obtenerPorId(Long id) {
		return this.repositorio.buscarPorId(id);
	}

	@Override
	public List<Solicitud> obtener() {
		return this.repositorio.findAll();
	}

	@Override
	public SolicitudesPaginados obtenerPaginado(int pagina, int tamano, String columna) {
		SolicitudesPaginados retorno = new SolicitudesPaginados();

		List<Solicitud> datos = this.repositorio
				.findAll(PageRequest.of(pagina - 1, tamano, Sort.by(columna).ascending())).toList();
		Solicitud[] datosArray = new Solicitud[datos.size()];
		datosArray = datos.toArray(datosArray);

		retorno.setDatos(datosArray);
		retorno.setPagina(new Pagina());
		retorno.getPagina().setTamano(datosArray.length);
		retorno.getPagina().setPaginaActual(pagina);
		retorno.getPagina()
				.setCantidadTotalPaginas((int) Math.ceil((double) this.repositorio.count() / (double) tamano));
		retorno.getPagina().setCantidadTotalElementos((int) this.repositorio.count());

		return retorno;
	}

	@Override
	public Long obtenerProxId() {
		Solicitud entidad = this.repositorio.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}

}
