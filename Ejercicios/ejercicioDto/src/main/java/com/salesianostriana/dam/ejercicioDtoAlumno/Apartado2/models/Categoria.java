package com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Categoria {
    private Long id;
    private String nombre;
}
