package com.talentotech.techlab2025.controller;

import com.talentotech.techlab2025.dto.PedidoCreacionDTO;
import com.talentotech.techlab2025.dto.PedidoRespuestaDTO;
import com.talentotech.techlab2025.model.Pedido;
import com.talentotech.techlab2025.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {

        List<Pedido> pedidos = pedidoService.listarPedidos();

        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoRespuestaDTO> buscarPedidoPorId(@PathVariable Long id) {

        PedidoRespuestaDTO pedido = pedidoService.buscarPedidoPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }
    @PostMapping
    public ResponseEntity<PedidoRespuestaDTO> crearPedido(@RequestBody PedidoCreacionDTO pedidoDto) {

        PedidoRespuestaDTO pedido = pedidoService.crearPedido(pedidoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id) {

        Pedido pedido = pedidoService.cancelarPedido(id);

        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

}
