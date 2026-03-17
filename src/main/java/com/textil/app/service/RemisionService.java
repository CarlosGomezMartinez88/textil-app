package com.textil.app.service;

import com.textil.app.entity.Remision;
import com.textil.app.repository.RemisionRepository;
import com.textil.app.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RemisionService {

    private final RemisionRepository remisionRepository;
    private final ClienteRepository clienteRepository;

    /**
     * Obtiene todas las remisiones
     */
    @Transactional(readOnly = true)
    public List<Remision> obtenerTodas() {
        return remisionRepository.findAll();
    }

    /**
     * Obtiene una remisión por su ID
     */
    @Transactional(readOnly = true)
    public Optional<Remision> obtenerPorId(Long id) {
        return remisionRepository.findById(id);
    }

    /**
     * Obtiene una remisión por su número de remisión
     */
    @Transactional(readOnly = true)
    public Optional<Remision> obtenerPorNumero(String numeroRemision) {
        return remisionRepository.findByNumeroRemision(numeroRemision);
    }

    /**
     * Obtiene todas las remisiones de un cliente
     */
    @Transactional(readOnly = true)
    public List<Remision> obtenerPorCliente(Long clienteId) {
        return remisionRepository.findByClienteId(clienteId);
    }

    /**
     * Crea una nueva remisión
     */
    public Remision crear(Remision remision) {
        return remisionRepository.save(remision);
    }

    /**
     * Actualiza una remisión existente
     */
    public Optional<Remision> actualizar(Long id, Remision remisionActualizada) {
        return remisionRepository.findById(id)
                .map(remision -> {
                    remision.setNumeroRemision(remisionActualizada.getNumeroRemision());
                    remision.setCliente(remisionActualizada.getCliente());
                    remision.setFechaRemision(remisionActualizada.getFechaRemision());
                    remision.setDireccionEntrega(remisionActualizada.getDireccionEntrega());
                    remision.setTelefonoContacto(remisionActualizada.getTelefonoContacto());
                    remision.setObservaciones(remisionActualizada.getObservaciones());
                    return remisionRepository.save(remision);
                });
    }

    /**
     * Elimina una remisión por su ID
     */
    public void eliminar(Long id) {
        remisionRepository.deleteById(id);
    }

    /**
     * Verifica si existe una remisión con el ID especificado
     */
    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return remisionRepository.existsById(id);
    }

}

