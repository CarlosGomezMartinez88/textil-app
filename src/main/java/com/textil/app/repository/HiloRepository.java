package com.textil.app.repository;

import com.textil.app.entity.Hilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HiloRepository extends JpaRepository<Hilo, Long> {
    
    Optional<Hilo> findByNombre(String nombre);
    
}

