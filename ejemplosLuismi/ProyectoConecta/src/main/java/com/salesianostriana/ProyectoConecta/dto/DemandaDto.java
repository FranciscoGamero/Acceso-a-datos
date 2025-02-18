package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Demanda;

public record DemandaDto(
        int cantidadAlumnos,
        String requisitos,
        String nombreEmpresa,
        String nombreCurso,
        String cursoEscolar,
        String nombreConvocatoria
    ) {
    public static DemandaDto of(Demanda demanda) {
        return new DemandaDto(demanda.getCantidadAlumnos(),
                demanda.getRequisitos(),
                demanda.getEmpresa().getNombre(),
                demanda.getCurso().getNombre(),
                demanda.getConvocatoria().getCursoEscolar(),
                demanda.getConvocatoria().getNombre());
    }
}
