package com.ict.perfiles.repositories;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.PerfilView;
import com.ict.commons.entity.PerfilViewEM;

@Repository
public interface IPerfilViewEMRepository extends DataTablesRepository<PerfilViewEM, Long> {
}
