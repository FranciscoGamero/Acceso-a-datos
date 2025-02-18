package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Empresa;
import com.salesianostriana.ProyectoConecta.models.FamiliaProfesional;
import com.salesianostriana.ProyectoConecta.models.Titulo;

import java.util.List;

public record FamiliaProfesionalDto(
        String nombre,
        List<TituloDto> listaTitulos,
        List<EmpresaDto> listaEmpresa) {


    public static FamiliaProfesionalDto of (FamiliaProfesional fp){

        return new FamiliaProfesionalDto(
                fp.getNombre(),
                fp.getTitulos().stream().map(TituloDto::of).toList(),
                fp.getListaEmpresas().stream().map(EmpresaDto::of).toList()
        );
    }

}
