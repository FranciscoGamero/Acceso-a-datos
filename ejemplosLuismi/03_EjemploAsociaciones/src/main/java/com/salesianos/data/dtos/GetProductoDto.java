package com.salesianos.data.dtos;

import com.salesianos.data.model.Categoria;
import com.salesianos.data.model.Producto;
import org.springframework.web.bind.annotation.GetMapping;

public record GetProductoDto(
        Long id,
        String nombre,
        double precio,
        GetCategoriaDto categoria
) {
    public static GetProductoDto of(Producto p) {
        return new GetProductoDto(
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                GetCategoriaDto.of(p.getCategoria())
        );
    }
}
