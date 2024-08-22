package com.ict.beneficios.repositories;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Beneficio;

@Repository
public interface IBeneficiosRepositoryTable extends DataTablesRepository<Beneficio, Long> {
}