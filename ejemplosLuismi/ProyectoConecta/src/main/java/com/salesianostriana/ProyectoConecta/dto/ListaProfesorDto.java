package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Profesor;

import java.util.List;

public record ListaProfesorDto (
        int cantidadProfesores,
        List<ProfesorDto>List){

    public static ListaProfesorDto of(List<Profesor> listaProfesor){

        return new ListaProfesorDto(listaProfesor.size(), listaProfesor.stream().map(ProfesorDto::of).toList());
    }
}
