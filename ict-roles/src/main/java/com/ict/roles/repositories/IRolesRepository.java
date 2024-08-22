package com.ict.roles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.RolesSistema;

@Repository
public interface IRolesRepository extends JpaRepository<RolesSistema, Long> {

	RolesSistema findFirstByOrderByIdDesc();
}
