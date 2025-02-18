package com.salesianostriana.ProyectoConecta.error;

public class FamiliaProfesionalNotFoundException extends RuntimeException {

    public FamiliaProfesionalNotFoundException(Long id) {
        super("Familia profesional " + id + " no encontrada");
    }
    public FamiliaProfesionalNotFoundException(String msg) {
        super(msg);
    }
    public FamiliaProfesionalNotFoundException() {
        super("No hay familia profesional con ese id de búsqueda");
    }
}
