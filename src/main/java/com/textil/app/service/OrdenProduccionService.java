package com.textil.app.service;

import com.textil.app.entity.OrdenProduccion;
import com.textil.app.repository.OrdenProduccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdenProduccionService {

    private final OrdenProduccionRepository ordenProduccionRepository;

    /**
     * Obtiene todas las órdenes de producción
     */
    @Transactional(readOnly = true)
    public List<OrdenProduccion> obtenerTodos() {
        return ordenProduccionRepository.findAll();
    }

    /**
     * Obtiene una orden de producción por su ID
     */
    @Transactional(readOnly = true)
    public Optional<OrdenProduccion> obtenerPorId(Long id) {
        return ordenProduccionRepository.findById(id);
    }

    /**
     * Obtiene una orden de producción por su número
     */
    @Transactional(readOnly = true)
    public Optional<OrdenProduccion> obtenerPorNumeroOrden(String numeroOrden) {
        return ordenProduccionRepository.findByNumeroOrden(numeroOrden);
    }

    /**
     * Crea una nueva orden de producción
     * Genera automáticamente el numeroOrden con formato OP-AAAA-XXXX
     */
    public OrdenProduccion crear(OrdenProduccion orden) {
        // Generar numeroOrden automáticamente
        String numeroOrden = generarNumeroOrden();
        orden.setNumeroOrden(numeroOrden);
        
        return ordenProduccionRepository.save(orden);
    }

    /**
     * Actualiza una orden de producción existente
     */
    public Optional<OrdenProduccion> actualizar(Long id, OrdenProduccion ordenActualizada) {
        return ordenProduccionRepository.findById(id)
                .map(orden -> {
                    orden.setCliente(ordenActualizada.getCliente());
                    orden.setHilo(ordenActualizada.getHilo());
                    orden.setPesoHiloKg(ordenActualizada.getPesoHiloKg());
                    orden.setEstado(ordenActualizada.getEstado());
                    return ordenProduccionRepository.save(orden);
                });
    }

    /**
     * Elimina una orden de producción por su ID
     */
    public void eliminar(Long id) {
        ordenProduccionRepository.deleteById(id);
    }

    /**
     * Verifica si existe una orden con el ID especificado
     */
    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return ordenProduccionRepository.existsById(id);
    }

    /**
     * Cuenta el total de órdenes de producción
     */
    @Transactional(readOnly = true)
    public long contarOrdenes() {
        return ordenProduccionRepository.count();
    }

    /**
     * Genera automáticamente el número de orden con formato OP-AAAA-XXXX
     * Ejemplo: OP-2026-0001
     */
    private String generarNumeroOrden() {
        int year = LocalDateTime.now().getYear();
        long consecutivo = ordenProduccionRepository.count() + 1;
        return String.format("OP-%d-%04d", year, consecutivo);
    }
}

