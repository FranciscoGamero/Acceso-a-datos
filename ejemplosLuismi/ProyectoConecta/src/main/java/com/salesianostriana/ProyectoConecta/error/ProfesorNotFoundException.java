package com.salesianostriana.ProyectoConecta.error;

public class ProfesorNotFoundException extends RuntimeException {

    public ProfesorNotFoundException(Long id) {
        super("Profesor " + id + " no encontrado");
    }
    public ProfesorNotFoundException(String msg) {
        super(msg);
    }
    public ProfesorNotFoundException() {
        super("No hay profesor con ese id de búsqueda");
    }
}
