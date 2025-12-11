package com.talentotech.techlab2025.mapper;

import com.talentotech.techlab2025.dto.PedidoCreacionDTO;
import com.talentotech.techlab2025.dto.PedidoRespuestaDTO;
import com.talentotech.techlab2025.dto.ProductoPedidoDTO;
import com.talentotech.techlab2025.dto.ProductoPedidoRespuestaDTO;
import com.talentotech.techlab2025.model.Cliente;
import com.talentotech.techlab2025.model.DetallePedido;
import com.talentotech.techlab2025.model.Pedido;
import com.talentotech.techlab2025.model.Producto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PedidoMapper {

    public Pedido creacionDtoToPedido(PedidoCreacionDTO creacionDto) {

        Pedido pedido = new Pedido();

        Cliente cliente = new Cliente();
        cliente.setId(creacionDto.idCliente());

        pedido.setCliente(cliente);

        Set<DetallePedido> detalles = new HashSet<>();

        for (ProductoPedidoDTO productoDto : creacionDto.productos()) {

            DetallePedido detalle = new DetallePedido();

            detalle.setCantidad(productoDto.cantidad());

            detalle.setPedido(pedido);

            Producto producto = new Producto();
            producto.setId(productoDto.idPoducto());

            detalle.setProducto(producto);

            detalles.add(detalle);
        }

        pedido.setDetalles(detalles);

        return pedido;
    }

    public PedidoRespuestaDTO pedidoToRespuestaDto(Pedido pedido) {

        Set<ProductoPedidoRespuestaDTO> productosDto = new HashSet<>();

        for (DetallePedido detalle : pedido.getDetalles()) {

            ProductoPedidoRespuestaDTO productoPedidoDto = new ProductoPedidoRespuestaDTO(
                    detalle.getProducto().getId(),
                    detalle.getProducto().getNombre(),
                    detalle.getProducto().getPrecio()
            );

            productosDto.add(productoPedidoDto);
        }

        PedidoRespuestaDTO respuestaDto = new PedidoRespuestaDTO(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNombre(),
                pedido.getFecha(),
                productosDto,
                pedido.getTotal(),
                pedido.getActivo()
        );

        return respuestaDto;
    }
}
