package com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2.dto;

import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2.models.Producto;

public record ProductoDto(
        String nombre,
        Double pvp,
        String imagen,
        String categoria
) {
    public static ProductoDto of(Producto producto) {
        return new ProductoDto(producto.getNombre(), producto.getPvp(),
                producto.getImagenes().getFirst(), producto.getCategoria().getNombre());
    }
}
