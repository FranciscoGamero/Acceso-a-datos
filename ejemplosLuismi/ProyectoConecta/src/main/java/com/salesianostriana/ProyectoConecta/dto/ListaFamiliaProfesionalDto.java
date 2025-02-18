package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.FamiliaProfesional;

import java.util.List;

public record ListaFamiliaProfesionalDto(
        int cantidadFamiliaProfesional,
        List<FamiliaProfesionalDto>  List) {

    public static ListaFamiliaProfesionalDto of (List<FamiliaProfesional> listaFamiliaProfesional){
        return new ListaFamiliaProfesionalDto(
                listaFamiliaProfesional.size(),
                listaFamiliaProfesional.stream().map(FamiliaProfesionalDto::of).toList()
        );
    }
}
