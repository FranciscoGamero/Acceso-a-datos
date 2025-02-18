package com.salesianostriana.ProyectoConecta.dto;


import com.salesianostriana.ProyectoConecta.models.Titulo;

import java.util.List;


public record TituloDto(String nombre,
                        int duracion,
                        String grado,
                        String nombreFamiliaprofesional,
                        List<CursoDto> listacurso) {

    public static TituloDto of (Titulo titulo){
        return new TituloDto(
                titulo.getNombre(),
                titulo.getDuracion(),
                titulo.getGrado(),
                titulo.getFamiliaProfesional() == null ? "No establecido" : titulo.getFamiliaProfesional().getNombre(),
                titulo.getCursos().stream().map(CursoDto::of).toList()
                );

    }

}
