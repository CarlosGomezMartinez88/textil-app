package com.textil.app.controller;

import com.textil.app.entity.RolloTela;
import com.textil.app.service.RolloTelaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rollos")
@RequiredArgsConstructor
@Validated
public class RolloTelaController {

    private final RolloTelaService rolloTelaService;

    /**
     * GET /rollos
     * Obtiene la lista de todos los rollos de tela
     */
    @GetMapping
    public ResponseEntity<List<RolloTela>> listarRollos() {
        List<RolloTela> rollos = rolloTelaService.obtenerTodos();
        return ResponseEntity.ok(rollos);
    }

    /**
     * GET /rollos/{id}
     * Obtiene un rollo específico por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<RolloTela> obtenerRollo(@PathVariable Long id) {
        return rolloTelaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /rollos/detalle/{detalleId}
     * Obtiene todos los rollos asociados a un DetalleOrdenProduccion
     */
    @GetMapping("/detalle/{detalleId}")
    public ResponseEntity<List<RolloTela>> obtenerRollosPorDetalle(@PathVariable Long detalleId) {
        List<RolloTela> rollos = rolloTelaService.obtenerPorDetalle(detalleId);
        return ResponseEntity.ok(rollos);
    }

    /**
     * POST /rollos/detalle/{detalleId}
     * Crea un nuevo rollo asociado a un DetalleOrdenProduccion
     *
     * Body de ejemplo:
     * {
     *   "pesoKg": 24.5,
     *   "fechaProduccion": "2026-03-13"
     * }
     */
    @PostMapping("/detalle/{detalleId}")
    public ResponseEntity<RolloTela> crearRollo(
            @PathVariable Long detalleId,
            @RequestBody RolloTela rollo) {
        try {
            RolloTela rolloGuardado = rolloTelaService.crear(detalleId, rollo);
            return ResponseEntity.status(HttpStatus.CREATED).body(rolloGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * PUT /rollos/{id}
     * Actualiza un rollo existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<RolloTela> actualizarRollo(
            @PathVariable Long id,
            @RequestBody RolloTela rolloActualizado) {
        return rolloTelaService.actualizar(id, rolloActualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /rollos/{id}
     * Elimina un rollo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRollo(@PathVariable Long id) {
        if (rolloTelaService.existe(id)) {
            rolloTelaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

