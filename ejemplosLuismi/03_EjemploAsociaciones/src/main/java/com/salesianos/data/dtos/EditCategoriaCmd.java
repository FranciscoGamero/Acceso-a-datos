package com.salesianos.data.dtos;

import com.salesianos.data.model.Producto;

import java.util.List;

public record EditCategoriaCmd(
        String nombre,
        List<Producto> listaProductos
) {
}
