package com.talentotech.techlab2025.controller;

import com.talentotech.techlab2025.model.Cliente;
import com.talentotech.techlab2025.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping
    public ResponseEntity<List<Cliente>> ListarClientes() {

        List<Cliente> clientes = clienteService.listaClientes();

        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {

        Cliente cliente = clienteService.buscarClientePorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente request) {
        Cliente cliente = clienteService.crearCliente(request.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente request) {

        Cliente cliente = clienteService.actualizarCliente(id, request.getNombre());

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }

}

