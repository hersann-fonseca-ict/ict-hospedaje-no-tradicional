package com.ict.perfiles.repositories;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.PerfilView;

@Repository
public interface IPerfilViewRepository extends DataTablesRepository<PerfilView, Long> {
}
