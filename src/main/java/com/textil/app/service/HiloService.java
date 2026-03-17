package com.textil.app.service;

import com.textil.app.entity.Hilo;
import com.textil.app.repository.HiloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HiloService {

    private final HiloRepository hiloRepository;

    /**
     * Obtiene todos los hilos
     */
    @Transactional(readOnly = true)
    public List<Hilo> obtenerTodos() {
        return hiloRepository.findAll();
    }

    /**
     * Obtiene un hilo por su ID
     */
    @Transactional(readOnly = true)
    public Optional<Hilo> obtenerPorId(Long id) {
        return hiloRepository.findById(id);
    }

    /**
     * Obtiene un hilo por su nombre
     */
    @Transactional(readOnly = true)
    public Optional<Hilo> obtenerPorNombre(String nombre) {
        return hiloRepository.findByNombre(nombre);
    }

    /**
     * Crea un nuevo hilo
     */
    public Hilo crear(Hilo hilo) {
        return hiloRepository.save(hilo);
    }

    /**
     * Actualiza un hilo existente
     */
    public Optional<Hilo> actualizar(Long id, Hilo hiloActualizado) {
        return hiloRepository.findById(id)
                .map(hilo -> {
                    hilo.setNombre(hiloActualizado.getNombre());
                    hilo.setPrecioKg(hiloActualizado.getPrecioKg());
                    hilo.setDescripcion(hiloActualizado.getDescripcion());
                    return hiloRepository.save(hilo);
                });
    }

    /**
     * Elimina un hilo por su ID
     */
    public void eliminar(Long id) {
        hiloRepository.deleteById(id);
    }

    /**
     * Verifica si existe un hilo con el ID especificado
     */
    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return hiloRepository.existsById(id);
    }

    /**
     * Cuenta el total de hilos
     */
    @Transactional(readOnly = true)
    public long contarHilos() {
        return hiloRepository.count();
    }
}

