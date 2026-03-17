package com.textil.app.repository;

import com.textil.app.entity.Remision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RemisionRepository extends JpaRepository<Remision, Long> {

    Optional<Remision> findByNumeroRemision(String numeroRemision);

    List<Remision> findByClienteId(Long clienteId);

}

