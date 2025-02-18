package com.salesianostriana.ProyectoConecta.error;

public class CursoNotFoundException extends RuntimeException {

    public CursoNotFoundException(Long id) {
        super("Curso " + id + " no encontrado");
    }
    public CursoNotFoundException(String msg) {
        super(msg);
    }
    public CursoNotFoundException() {
        super("No hay curso con ese id de búsqueda");
    }
}
