package com.talentotech.techlab2025.service;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import com.talentotech.techlab2025.dto.PedidoCreacionDTO;
import com.talentotech.techlab2025.dto.PedidoRespuestaDTO;
import com.talentotech.techlab2025.mapper.PedidoMapper;
import com.talentotech.techlab2025.model.Cliente;
import com.talentotech.techlab2025.model.DetallePedido;
import com.talentotech.techlab2025.model.Pedido;
import com.talentotech.techlab2025.model.Producto;
import com.talentotech.techlab2025.repository.ClienteRepository;
import com.talentotech.techlab2025.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoService productoService;
    private final PedidoMapper pedidoMapper;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProductoService productoService,PedidoMapper pedidoMapper) {

        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoService = productoService;
        this.pedidoMapper = pedidoMapper;
    }
    public List<Pedido> listarPedidos() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos;
    }
    public PedidoRespuestaDTO buscarPedidoPorId(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el pedido con la ID: " + id));

        return pedidoMapper.pedidoToRespuestaDto(pedido);
    }
    @Transactional
    public PedidoRespuestaDTO crearPedido(PedidoCreacionDTO pedidoDto) {

        Pedido nuevoPedido = pedidoMapper.creacionDtoToPedido(pedidoDto);

        Cliente cliente = clienteRepository.findById(nuevoPedido.getCliente().getId()).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el cliente con la ID: " + nuevoPedido.getCliente().getId()));

        nuevoPedido.setCliente(cliente);
        nuevoPedido.setFecha(LocalDateTime.now());

        BigDecimal precioTotal = BigDecimal.ZERO;

        for (DetallePedido detalle : nuevoPedido.getDetalles()) {

            Long idProducto = detalle.getProducto().getId();
            int stockPedido = detalle.getCantidad();

            Producto producto = productoService.validarYReservar(idProducto, stockPedido);

            detalle.setPedido(nuevoPedido);
            detalle.setProducto(producto);
            detalle.setPrecio(producto.getPrecio());

            BigDecimal precio = detalle.getPrecio().multiply(BigDecimal.valueOf(stockPedido));
            precioTotal = precioTotal.add(precio);
        }
        nuevoPedido.setTotal(precioTotal);

        nuevoPedido.setActivo(Boolean.TRUE);

        return pedidoMapper.pedidoToRespuestaDto(pedidoRepository.save(nuevoPedido));
    }
    @Transactional
    public Pedido cancelarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el pedido con la ID: " + id));

        for (DetallePedido detalle : pedido.getDetalles()) {

            productoService.recuperarStock(detalle.getProducto().getId(), detalle.getCantidad());
        }

        pedido.setActivo(Boolean.FALSE);

        return pedidoRepository.save(pedido);
    }
}
