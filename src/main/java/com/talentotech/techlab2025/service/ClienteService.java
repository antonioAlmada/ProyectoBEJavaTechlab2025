package com.talentotech.techlab2025.service;

import com.talentotech.techlab2025.model.Cliente;
import com.talentotech.techlab2025.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public List<Cliente> listaClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes;
    }
    public Cliente buscarClientePorId(Long id) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el cliente con la ID: " + id));

        return cliente;
    }
    public Cliente crearCliente(String nombre) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);

        clienteRepository.save(cliente);

        return cliente;
    }
    public Cliente actualizarCliente(Long id, String nombre) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el cliente con la ID: " + id));

        cliente.setNombre(nombre);
        clienteRepository.save(cliente);

        return cliente;
    }
    public void eliminarCliente(Long id) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el cliente con la ID: " + id));

        clienteRepository.delete(cliente);
    }
}
