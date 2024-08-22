package com.ict.direccion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ict.commons.entity.Provincia; 

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Long> {

}
