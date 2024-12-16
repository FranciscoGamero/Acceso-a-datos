package com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Direccion {
    private Long id;
    private String tipoVia;
    private String linea1;
    private String linea2;
    private String cp;
    private Long poblacion;
    private String provincia;
}
