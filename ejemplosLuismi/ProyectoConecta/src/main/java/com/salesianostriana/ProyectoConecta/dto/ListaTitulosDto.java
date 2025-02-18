package com.salesianostriana.ProyectoConecta.dto;



import java.util.List;

public record ListaTitulosDto(int cantidadTitulos, List<TituloDto> List) {

    public static ListaTitulosDto of (List<TituloDto> listaTitulo){
        return new ListaTitulosDto(listaTitulo.size(), listaTitulo);
    }
}
