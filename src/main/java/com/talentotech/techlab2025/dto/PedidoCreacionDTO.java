package com.talentotech.techlab2025.dto;

import java.util.Set;

public record PedidoCreacionDTO(Long idCliente, Set<ProductoPedidoDTO> productos) {
}
