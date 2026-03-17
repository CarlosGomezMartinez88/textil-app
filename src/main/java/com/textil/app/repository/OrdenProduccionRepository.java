package com.textil.app.repository;

import com.textil.app.entity.OrdenProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion, Long> {
    
    Optional<OrdenProduccion> findByNumeroOrden(String numeroOrden);
    
}

