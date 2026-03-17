package com.textil.app.service;

import com.textil.app.entity.DetalleOrdenProduccion;
import com.textil.app.entity.RolloTela;
import com.textil.app.repository.DetalleOrdenProduccionRepository;
import com.textil.app.repository.RolloTelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RolloTelaService {

    private final RolloTelaRepository rolloTelaRepository;
    private final DetalleOrdenProduccionRepository detalleOrdenProduccionRepository;

    /**
     * Obtiene todos los rollos de tela
     */
    @Transactional(readOnly = true)
    public List<RolloTela> obtenerTodos() {
        return rolloTelaRepository.findAll();
    }

    /**
     * Obtiene un rollo por su ID
     */
    @Transactional(readOnly = true)
    public Optional<RolloTela> obtenerPorId(Long id) {
        return rolloTelaRepository.findById(id);
    }

    /**
     * Obtiene todos los rollos asociados a un DetalleOrdenProduccion
     */
    @Transactional(readOnly = true)
    public List<RolloTela> obtenerPorDetalle(Long detalleId) {
        return rolloTelaRepository.findByDetalleOrdenProduccionId(detalleId);
    }

    /**
     * Crea un nuevo rollo de tela asociado a un DetalleOrdenProduccion
     */
    public RolloTela crear(Long detalleId, RolloTela rollo) {
        DetalleOrdenProduccion detalle = detalleOrdenProduccionRepository.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("DetalleOrdenProduccion no encontrado con id: " + detalleId));
        
        rollo.setDetalleOrdenProduccion(detalle);
        return rolloTelaRepository.save(rollo);
    }

    /**
     * Actualiza un rollo existente
     */
    public Optional<RolloTela> actualizar(Long id, RolloTela rolloActualizado) {
        return rolloTelaRepository.findById(id)
                .map(rollo -> {
                    rollo.setPesoKg(rolloActualizado.getPesoKg());
                    rollo.setFechaProduccion(rolloActualizado.getFechaProduccion());
                    rollo.setEstado(rolloActualizado.getEstado());
                    return rolloTelaRepository.save(rollo);
                });
    }

    /**
     * Elimina un rollo por su ID
     */
    public void eliminar(Long id) {
        rolloTelaRepository.deleteById(id);
    }

    /**
     * Verifica si existe un rollo con el ID especificado
     */
    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return rolloTelaRepository.existsById(id);
    }

    /**
     * Cuenta el total de rollos
     */
    @Transactional(readOnly = true)
    public long contarRollos() {
        return rolloTelaRepository.count();
    }
}

