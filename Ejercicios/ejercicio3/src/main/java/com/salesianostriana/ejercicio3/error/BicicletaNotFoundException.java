package com.salesianostriana.ejercicio3.error;

public class BicicletaNotFoundException extends RuntimeException {
    public BicicletaNotFoundException(Long id) {
        super("No hay bicicleta con ese ID: %d".formatted(id));
    }

    public BicicletaNotFoundException(String msg) {
        super(msg);
    }

    public BicicletaNotFoundException() {
        super("No hay bicicletas con esos requisitos de búsqueda");
    }
}
