package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Convocatoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ConvocatoriaDto(
        @NotEmpty
        @NotBlank
        String nombre,
        @NotEmpty
        @NotBlank
        String cursoEscolar,
        @NotEmpty
        List<DemandaDto> listaDemanda) {

    public static ConvocatoriaDto of(Convocatoria convocatoria) {
        return new ConvocatoriaDto(
                convocatoria.getNombre(),
                convocatoria.getCursoEscolar(),
                convocatoria.getListaDemandas().stream().map(DemandaDto::of).toList()
        );
    }
}