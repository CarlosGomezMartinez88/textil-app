package com.textil.app.repository;

import com.textil.app.entity.RolloTela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolloTelaRepository extends JpaRepository<RolloTela, Long> {
    
    List<RolloTela> findByDetalleOrdenProduccionId(Long detalleId);
    
}

