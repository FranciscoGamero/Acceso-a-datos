package com.salesianos.data.dtos;

public record EditProductoCmd(
        String nombre,
        String descripcion,
        double precio,
        Long categoriaId
) {
}
