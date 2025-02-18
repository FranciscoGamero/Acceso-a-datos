package com.salesianostriana.ProyectoConecta.error;

public class TituloNotFoundException extends RuntimeException {

    public TituloNotFoundException(Long id) {
        super("Titulo " + id + " no encontrado");
    }
    public TituloNotFoundException(String msg) {
        super(msg);
    }
    public TituloNotFoundException() {
        super("No hay titulo con ese id de búsqueda");
    }
}
