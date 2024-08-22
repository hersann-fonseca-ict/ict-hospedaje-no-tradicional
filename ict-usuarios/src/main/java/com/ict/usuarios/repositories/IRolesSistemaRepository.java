package com.ict.usuarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ict.commons.entity.RolesSistema;

@Repository
public interface IRolesSistemaRepository extends JpaRepository<RolesSistema, Long> {

}
