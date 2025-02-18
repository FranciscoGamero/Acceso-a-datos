package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Titulo;

public record EditTituloDto(String nombre, int duracion, String grado, FamiliaProfesionalDto familiaProfesional) {

    public static EditTituloDto of (Titulo titulo){
        return new EditTituloDto(
                titulo.getNombre(),
                titulo.getDuracion(),
                titulo.getGrado(),
                FamiliaProfesionalDto.of(titulo.getFamiliaProfesional()));
    }

}