package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.FamiliaProfesional;

import java.util.List;

public record EditFamiliaProfesionalDto(
        String nombre,
        List<TituloDto> listaTitulos,
        List<EmpresaDto> listaEmpresas) {


    public static EditFamiliaProfesionalDto of (FamiliaProfesional fp){

        return new EditFamiliaProfesionalDto(
                fp.getNombre(),
                fp.getTitulos().stream().map(TituloDto::of).toList(),
                fp.getListaEmpresas().stream().map(EmpresaDto::of).toList()
                );
    }

}
