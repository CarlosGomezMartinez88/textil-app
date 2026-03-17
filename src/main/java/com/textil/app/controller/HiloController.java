package com.textil.app.controller;

import com.textil.app.entity.Hilo;
import com.textil.app.service.HiloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hilos")
@RequiredArgsConstructor
@Validated
public class HiloController {

    private final HiloService hiloService;

    /**
     * GET /hilos
     * Obtiene la lista de todos los hilos
     */
    @GetMapping
    public ResponseEntity<List<Hilo>> listarHilos() {
        List<Hilo> hilos = hiloService.obtenerTodos();
        return ResponseEntity.ok(hilos);
    }

    /**
     * GET /hilos/{id}
     * Obtiene un hilo específico por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hilo> obtenerHilo(@PathVariable Long id) {
        return hiloService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /hilos
     * Crea un nuevo hilo
     */
    @PostMapping
    public ResponseEntity<Hilo> crearHilo(@RequestBody Hilo hilo) {
        Hilo hiloGuardado = hiloService.crear(hilo);
        return ResponseEntity.status(HttpStatus.CREATED).body(hiloGuardado);
    }

    /**
     * PUT /hilos/{id}
     * Actualiza un hilo existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hilo> actualizarHilo(
            @PathVariable Long id,
            @RequestBody Hilo hiloActualizado) {
        return hiloService.actualizar(id, hiloActualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /hilos/{id}
     * Elimina un hilo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHilo(@PathVariable Long id) {
        if (hiloService.existe(id)) {
            hiloService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

