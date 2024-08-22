package com.ict.usuarios.repositories;


import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.UsuarioSistema;




@Repository
public interface IUsuarioRepositoryTable extends DataTablesRepository<UsuarioSistema, Long> {
	
	public List<UsuarioSistema> findAll();
}
