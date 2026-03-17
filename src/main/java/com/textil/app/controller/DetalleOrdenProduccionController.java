package com.textil.app.controller;

import com.textil.app.entity.DetalleOrdenProduccion;
import com.textil.app.service.DetalleOrdenProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-orden")
@RequiredArgsConstructor
@Validated
public class DetalleOrdenProduccionController {

    private final DetalleOrdenProduccionService detalleOrdenProduccionService;

    /**
     * GET /detalles-orden
     * Obtiene la lista de todos los detalles de órdenes de producción
     */
    @GetMapping
    public ResponseEntity<List<DetalleOrdenProduccion>> listarDetalles() {
        List<DetalleOrdenProduccion> detalles = detalleOrdenProduccionService.obtenerTodos();
        return ResponseEntity.ok(detalles);
    }

    /**
     * GET /detalles-orden/{id}
     * Obtiene un detalle específico por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrdenProduccion> obtenerDetalle(@PathVariable Long id) {
        return detalleOrdenProduccionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /detalles-orden/orden/{ordenId}
     * Obtiene los detalles de una orden de producción específica
     */
    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<List<DetalleOrdenProduccion>> obtenerDetallesPorOrden(@PathVariable Long ordenId) {
        List<DetalleOrdenProduccion> detalles = detalleOrdenProduccionService.obtenerPorOrden(ordenId);
        return ResponseEntity.ok(detalles);
    }

    /**
     * POST /detalles-orden
     * Crea un nuevo detalle de orden de producción
     */
    @PostMapping
    public ResponseEntity<DetalleOrdenProduccion> crearDetalle(@RequestBody DetalleOrdenProduccion detalle) {
        DetalleOrdenProduccion detalleGuardado = detalleOrdenProduccionService.crear(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleGuardado);
    }

    /**
     * PUT /detalles-orden/{id}
     * Actualiza un detalle de orden existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrdenProduccion> actualizarDetalle(
            @PathVariable Long id,
            @RequestBody DetalleOrdenProduccion detalleActualizado) {
        return detalleOrdenProduccionService.actualizar(id, detalleActualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /detalles-orden/{id}
     * Elimina un detalle de orden
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        if (detalleOrdenProduccionService.existe(id)) {
            detalleOrdenProduccionService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

