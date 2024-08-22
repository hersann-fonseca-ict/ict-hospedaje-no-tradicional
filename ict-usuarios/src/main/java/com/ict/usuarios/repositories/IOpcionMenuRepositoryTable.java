package com.ict.usuarios.repositories;


import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.OpcionesMenu;





@Repository
public interface IOpcionMenuRepositoryTable extends DataTablesRepository<OpcionesMenu, Long> {
}
