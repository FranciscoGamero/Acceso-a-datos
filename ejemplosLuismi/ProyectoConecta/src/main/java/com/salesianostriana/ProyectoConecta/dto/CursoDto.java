package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Curso;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record CursoDto(
        @NotEmpty
        @NotBlank
        @NotNull
        String nombre,
        @Min(1)
        int horasEmpresa,
        @NotEmpty
        List<DemandaDto> listaDemandas,
        @NotEmpty
        @NotBlank
        String cursoNombre
                       ) {

    public static CursoDto of (Curso curso){
        return new CursoDto(curso.getNombre(),
                curso.getHorasEmpresa(),
                curso.getListaDemandas().stream().map(DemandaDto::of).toList(),
                curso.getTitulo().getNombre()
        );
    }
}
