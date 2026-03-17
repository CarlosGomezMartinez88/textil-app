package com.textil.app.controller;

import com.textil.app.entity.OrdenProduccion;
import com.textil.app.service.OrdenProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
@Validated
public class OrdenProduccionController {

    private final OrdenProduccionService ordenProduccionService;

    /**
     * GET /ordenes
     * Obtiene la lista de todas las órdenes de producción
     */
    @GetMapping
    public ResponseEntity<List<OrdenProduccion>> listarOrdenes() {
        List<OrdenProduccion> ordenes = ordenProduccionService.obtenerTodos();
        return ResponseEntity.ok(ordenes);
    }

    /**
     * GET /ordenes/{id}
     * Obtiene una orden de producción específica por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdenProduccion> obtenerOrden(@PathVariable Long id) {
        return ordenProduccionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /ordenes/numero/{numeroOrden}
     * Obtiene una orden de producción por su número
     */
    @GetMapping("/numero/{numeroOrden}")
    public ResponseEntity<OrdenProduccion> obtenerOrdenPorNumero(@PathVariable String numeroOrden) {
        return ordenProduccionService.obtenerPorNumeroOrden(numeroOrden)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /ordenes
     * Crea una nueva orden de producción
     * El numeroOrden se genera automáticamente
     */
    @PostMapping
    public ResponseEntity<OrdenProduccion> crearOrden(@RequestBody OrdenProduccion orden) {
        OrdenProduccion ordenGuardada = ordenProduccionService.crear(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenGuardada);
    }

    /**
     * PUT /ordenes/{id}
     * Actualiza una orden de producción existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrdenProduccion> actualizarOrden(
            @PathVariable Long id,
            @RequestBody OrdenProduccion ordenActualizada) {
        return ordenProduccionService.actualizar(id, ordenActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /ordenes/{id}
     * Elimina una orden de producción
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        if (ordenProduccionService.existe(id)) {
            ordenProduccionService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

