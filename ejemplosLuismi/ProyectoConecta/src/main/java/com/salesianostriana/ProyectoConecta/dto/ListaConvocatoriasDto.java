package com.salesianostriana.ProyectoConecta.dto;

import java.util.List;

public record ListaConvocatoriasDto(int cantidadConvocatorias, List<ConvocatoriaDto> convocatorias) {

    public static ListaConvocatoriasDto of(List<ConvocatoriaDto> convocatorias) {
        return new ListaConvocatoriasDto(convocatorias.size(), convocatorias);
    }
}
