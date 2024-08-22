package com.ict.secuencia.repositories;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.ict.commons.entity.Secuencia;

@Repository
public interface ISecuencia extends JpaRepository<Secuencia, String> {
 	 
}
