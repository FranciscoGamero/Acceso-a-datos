package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Curso;


public record EditCursoDto(String nombre, int horasEmpresa) {

    public static EditCursoDto of (Curso curso){
        return new EditCursoDto(curso.getNombre(), curso.getHorasEmpresa());
    }
}
