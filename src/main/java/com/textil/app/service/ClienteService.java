package com.textil.app.service;

import com.textil.app.entity.Cliente;
import com.textil.app.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    /**
     * Obtiene todos los clientes
     */
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Obtiene un cliente por su ID
     */
    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Obtiene un cliente por su NIT
     */
    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerPorNit(String nit) {
        return clienteRepository.findByNit(nit);
    }

    /**
     * Crea un nuevo cliente
     */
    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Actualiza un cliente existente
     */
    public Optional<Cliente> actualizar(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(clienteActualizado.getNombre());
                    cliente.setNit(clienteActualizado.getNit());
                    cliente.setDepartamento(clienteActualizado.getDepartamento());
                    cliente.setCiudad(clienteActualizado.getCiudad());
                    cliente.setTelefono(clienteActualizado.getTelefono());
                    cliente.setCorreo(clienteActualizado.getCorreo());
                    cliente.setContacto(clienteActualizado.getContacto());
                    cliente.setDireccion(clienteActualizado.getDireccion());
                    return clienteRepository.save(cliente);
                });
    }

    /**
     * Elimina un cliente por su ID
     */
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Verifica si existe un cliente con el ID especificado
     */
    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return clienteRepository.existsById(id);
    }

    /**
     * Cuenta el total de clientes
     */
    @Transactional(readOnly = true)
    public long contarClientes() {
        return clienteRepository.count();
    }
}

