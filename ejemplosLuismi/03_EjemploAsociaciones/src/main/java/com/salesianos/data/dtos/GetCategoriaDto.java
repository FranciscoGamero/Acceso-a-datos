package com.salesianos.data.dtos;

import com.salesianos.data.model.Categoria;

public record GetCategoriaDto(
        Long id,
        String nombre
) {
    public static GetCategoriaDto of(Categoria categoria) {
        return new GetCategoriaDto(categoria.getId(), categoria.getNombreCategoria());
    }
}
