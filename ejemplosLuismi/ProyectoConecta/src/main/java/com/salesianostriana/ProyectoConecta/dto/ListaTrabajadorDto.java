package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Trabajador;

import java.util.List;

public record ListaTrabajadorDto(
        int cantidadTrabajadores,
        List<TrabajadorDto> listaTrabajadores){

    public static ListaTrabajadorDto of(List<TrabajadorDto> lista){
        return new ListaTrabajadorDto(lista.size(), lista);
    }
}

