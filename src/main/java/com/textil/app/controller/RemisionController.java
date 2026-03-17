package com.textil.app.controller;

import com.textil.app.entity.Remision;
import com.textil.app.service.RemisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remisiones")
@RequiredArgsConstructor
@Validated
public class RemisionController {

    private final RemisionService remisionService;

    /**
     * GET /remisiones
     * Obtiene la lista de todas las remisiones
     */
    @GetMapping
    public ResponseEntity<List<Remision>> listarRemisiones() {
        List<Remision> remisiones = remisionService.obtenerTodas();
        return ResponseEntity.ok(remisiones);
    }

    /**
     * GET /remisiones/{id}
     * Obtiene una remisión específica por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Remision> obtenerRemision(@PathVariable Long id) {
        return remisionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /remisiones/numero/{numeroRemision}
     * Obtiene una remisión por su número
     */
    @GetMapping("/numero/{numeroRemision}")
    public ResponseEntity<Remision> obtenerRemisionPorNumero(@PathVariable String numeroRemision) {
        return remisionService.obtenerPorNumero(numeroRemision)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /remisiones/cliente/{clienteId}
     * Obtiene todas las remisiones de un cliente
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Remision>> obtenerRemisionesPorCliente(@PathVariable Long clienteId) {
        List<Remision> remisiones = remisionService.obtenerPorCliente(clienteId);
        return ResponseEntity.ok(remisiones);
    }

    /**
     * POST /remisiones
     * Crea una nueva remisión
     */
    @PostMapping
    public ResponseEntity<Remision> crearRemision(@RequestBody Remision remision) {
        Remision remisionGuardada = remisionService.crear(remision);
        return ResponseEntity.status(HttpStatus.CREATED).body(remisionGuardada);
    }

    /**
     * PUT /remisiones/{id}
     * Actualiza una remisión existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Remision> actualizarRemision(
            @PathVariable Long id,
            @RequestBody Remision remisionActualizada) {
        return remisionService.actualizar(id, remisionActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /remisiones/{id}
     * Elimina una remisión
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRemision(@PathVariable Long id) {
        if (remisionService.existe(id)) {
            remisionService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

