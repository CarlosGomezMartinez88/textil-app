package com.textil.app.service;

import com.textil.app.entity.DetalleOrdenProduccion;
import com.textil.app.repository.DetalleOrdenProduccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DetalleOrdenProduccionService {

    private final DetalleOrdenProduccionRepository detalleOrdenProduccionRepository;

    /**
     * Obtiene todos los detalles de órdenes de producción
     */
    @Transactional(readOnly = true)
    public List<DetalleOrdenProduccion> obtenerTodos() {
        return detalleOrdenProduccionRepository.findAll();
    }

    /**
     * Obtiene un detalle de orden por su ID
     */
    @Transactional(readOnly = true)
    public Optional<DetalleOrdenProduccion> obtenerPorId(Long id) {
        return detalleOrdenProduccionRepository.findById(id);
    }

    /**
     * Obtiene los detalles de una orden de producción específica
     */
    @Transactional(readOnly = true)
    public List<DetalleOrdenProduccion> obtenerPorOrden(Long ordenId) {
        return detalleOrdenProduccionRepository.findByOrdenProduccionId(ordenId);
    }

    /**
     * Crea un nuevo detalle de orden de producción
     */
    public DetalleOrdenProduccion crear(DetalleOrdenProduccion detalle) {
        return detalleOrdenProduccionRepository.save(detalle);
    }

    /**
     * Actualiza un detalle de orden existente
     */
    public Optional<DetalleOrdenProduccion> actualizar(Long id, DetalleOrdenProduccion detalleActualizado) {
        return detalleOrdenProduccionRepository.findById(id)
                .map(detalle -> {
                    detalle.setOrdenProduccion(detalleActualizado.getOrdenProduccion());
                    detalle.setHilo(detalleActualizado.getHilo());
                    detalle.setPesoHiloKg(detalleActualizado.getPesoHiloKg());
                    return detalleOrdenProduccionRepository.save(detalle);
                });
    }

    /**
     * Elimina un detalle de orden por su ID
     */
    public void eliminar(Long id) {
        detalleOrdenProduccionRepository.deleteById(id);
    }

    /**
     * Verifica si existe un detalle con el ID especificado
     */
    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return detalleOrdenProduccionRepository.existsById(id);
    }

    /**
     * Cuenta el total de detalles de órdenes
     */
    @Transactional(readOnly = true)
    public long contarDetalles() {
        return detalleOrdenProduccionRepository.count();
    }
}

