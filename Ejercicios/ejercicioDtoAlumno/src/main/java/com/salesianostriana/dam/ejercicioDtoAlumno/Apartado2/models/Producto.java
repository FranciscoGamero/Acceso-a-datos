package com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double pvp;
    private List<String> imagenes;
    private Categoria categoria;
}
