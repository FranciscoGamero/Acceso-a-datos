package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Curso;

import java.util.List;

public record ListaCursoDto(int cantidadCurso, List<CursoDto>List) {

    public static ListaCursoDto of (List<Curso> listaCursos){
        return new ListaCursoDto(listaCursos.size(), listaCursos.stream().map(CursoDto::of).toList());
    }
}
