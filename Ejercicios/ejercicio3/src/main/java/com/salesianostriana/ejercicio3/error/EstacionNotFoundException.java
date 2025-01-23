package com.salesianostriana.ejercicio3.error;

public class EstacionNotFoundException extends RuntimeException {
    public EstacionNotFoundException(Long id) {
        super("No hay estaciones con ese ID: %d".formatted(id));
    }

    public EstacionNotFoundException(String msg) {
        super(msg);
    }

    public EstacionNotFoundException() {
        super("No hay estaciones con esos requisitos de búsqueda");
    }
}
