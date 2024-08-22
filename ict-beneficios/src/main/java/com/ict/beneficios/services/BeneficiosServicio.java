package com.ict.beneficios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ict.beneficios.models.BeneficiosPaginados;
import com.ict.beneficios.models.Pagina;
import com.ict.beneficios.repositories.IBeneficiosRepository;
import com.ict.commons.entity.Beneficio;

@Service
public class BeneficiosServicio implements IBeneficiosService {

	@Autowired
	private IBeneficiosRepository repository;
	
	@Override
	public Beneficio guardar(Beneficio beneficio) {
		beneficio.setId(this.obtenerProxId());
		return this.repository.save(beneficio);
	}

	@Override
	public Beneficio obtenerPorId(Long id) {
		var valor = this.repository.findById(id);
		if(valor != null) {
			return valor.get();			
		}
		return null;
	}

	@Override
	public Beneficio obtenerPorNombre(Beneficio beneficio) {
		List<Beneficio> beneficios = this.repository.findByNombreBeneficio(beneficio.getNombreBeneficio());
		 if(!beneficios.isEmpty() && beneficio.getId() != null ) {
			 return beneficios.get(0).getId() == beneficio.getId() ? null : beneficios.get(0);
		 }else if(!beneficios.isEmpty()){
			 return beneficios.get(0); 
		 }
			return null;
	}
	@Override
	public List<Beneficio> obtener() {
		return this.repository.findAll();
	}

	@Override
	public BeneficiosPaginados obtenerPaginado(int pagina, int tamano, String columna) {
		BeneficiosPaginados retorno = new BeneficiosPaginados();
		
		List<Beneficio> datos = this.repository.findAll(PageRequest.of(pagina - 1, tamano, Sort.by(columna).ascending())).toList();
		Beneficio[] datosArray = new Beneficio[datos.size()];
		datosArray = datos.toArray(datosArray);
		
		retorno.setDatos(datosArray);
		retorno.setPagina(new Pagina());
		retorno.getPagina().setTamano(datosArray.length);
		retorno.getPagina().setPaginaActual(pagina);
		retorno.getPagina().setCantidadTotalPaginas((int)Math.ceil((double)this.repository.count()/(double)tamano));
		retorno.getPagina().setCantidadTotalElementos((int)this.repository.count());
		
		return retorno;
	}
	
	@Override
	public BeneficiosPaginados obtenerPrueba() {
		BeneficiosPaginados retorno = new BeneficiosPaginados();
		
		List<Beneficio> datos = this.repository.findAll();
		Beneficio[] datosArray = new Beneficio[datos.size()];
		datosArray = datos.toArray(datosArray);
		
		retorno.setDatos(datosArray);
		retorno.setPagina(new Pagina());
		retorno.getPagina().setTamano(datosArray.length);
		retorno.getPagina().setCantidadTotalElementos((int)this.repository.count());
		
		return retorno;
	}
	
	@Override
	public Long obtenerProxId() {
		Beneficio entidad = this.repository.findFirstByOrderByIdDesc();
		return entidad != null && entidad.getId() != null ? entidad.getId() + 1L : 1L;
	}

}
