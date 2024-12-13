package com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Curso {
    private Long id;
    private String nombre;
    private String tipo;
    private String tutor;
    private String aula;
}
