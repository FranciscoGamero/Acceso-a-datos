package com.salesianostriana.ProyectoConecta.dto;

import java.util.List;

public record ListaDemandaDto(
        int cantidadDemandas,
        List<DemandaDto> listaDemandas
) {

    public static ListaDemandaDto of(List<DemandaDto> listaDemandas){
        return new ListaDemandaDto(listaDemandas.size(), listaDemandas);
    }
}
