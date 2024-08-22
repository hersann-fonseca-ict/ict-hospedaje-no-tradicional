package com.ict.solicitudes.repositories;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;
import com.ict.commons.entity.Solicitud;

@Repository
public interface ISolicitudRepositoryTabla  extends DataTablesRepository<Solicitud, Long> {


}
