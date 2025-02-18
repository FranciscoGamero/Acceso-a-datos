package com.salesianostriana.ProyectoConecta.dto;

import java.util.List;

public record ListaEmpresaDto(int cantidadEmpresas, List<EmpresaDto> listaEmpresas) {

    public static ListaEmpresaDto of(List<EmpresaDto> listaEmpresas) {
        return new ListaEmpresaDto(listaEmpresas.size(), listaEmpresas);
    }
}
