package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Demanda;
import com.salesianostriana.ProyectoConecta.models.Empresa;
import com.salesianostriana.ProyectoConecta.models.FamiliaProfesional;

import java.util.List;
import java.util.Set;

public record EmpresaDto(
        String cif,
        String direccion,
        String coordenadas,
        String nombre,
        List<DemandaDto> listaDemandas
) {

    public static EmpresaDto of(Empresa empresa) {
        return new EmpresaDto(
                empresa.getCif(),
                empresa.getDireccion(),
                empresa.getCoordenadas(),
                empresa.getNombre(),
                empresa.getListaDemandas().stream().map(DemandaDto::of).toList()
        );
    }
}
