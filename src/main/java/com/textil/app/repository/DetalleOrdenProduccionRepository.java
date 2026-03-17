package com.textil.app.repository;

import com.textil.app.entity.DetalleOrdenProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleOrdenProduccionRepository extends JpaRepository<DetalleOrdenProduccion, Long> {
    
    List<DetalleOrdenProduccion> findByOrdenProduccionId(Long ordenId);
    
}

