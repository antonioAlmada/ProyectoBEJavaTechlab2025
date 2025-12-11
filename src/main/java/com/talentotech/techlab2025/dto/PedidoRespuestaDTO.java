package com.talentotech.techlab2025.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record PedidoRespuestaDTO(
        Long idPedido,
        Long idCliente,
        String nombreCliente,
        LocalDateTime fechaPedido,
        Set<ProductoPedidoRespuestaDTO> productos,
        BigDecimal precioTotal,
        Boolean activo
) {
}
