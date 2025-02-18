package com.salesianostriana.ProyectoConecta.dto.ContactoPackage;



import java.util.List;

public record ListaContactosDto(
        int cantidadContactos, List<ContactoDto> listaContactos
) {
    public static ListaContactosDto of(List<ContactoDto> listaContactos) {
        return new ListaContactosDto(listaContactos.size(), listaContactos);
    }
}
