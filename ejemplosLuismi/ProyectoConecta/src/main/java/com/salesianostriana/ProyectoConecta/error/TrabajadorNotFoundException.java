package com.salesianostriana.ProyectoConecta.error;

public class TrabajadorNotFoundException extends RuntimeException {

    public TrabajadorNotFoundException(Long id) {
        super("Trabajador " + id + " no encontrado");
    }
    public TrabajadorNotFoundException(String msg) {
        super(msg);
    }
    public TrabajadorNotFoundException() {
        super("No hay trabajadores con ese id de búsqueda");
    }
}
