package com.salesianos.data.dtos;

import com.salesianos.data.model.Categoria;

public record GetCategoriaDto(
        Long id,
        String nombre
) {
    public static GetCategoriaDto of(Categoria c){
        return new GetCategoriaDto(c.getId(), c.getNombreCategoria());
    }
}
