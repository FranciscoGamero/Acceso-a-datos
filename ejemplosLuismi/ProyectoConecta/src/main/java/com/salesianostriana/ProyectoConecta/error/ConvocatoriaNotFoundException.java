package com.salesianostriana.ProyectoConecta.error;

public class ConvocatoriaNotFoundException extends RuntimeException {

    public ConvocatoriaNotFoundException(Long id) {
        super("Convocatoria " + id + " no encontrado");
    }
    public ConvocatoriaNotFoundException(String msg) {
        super(msg);
    }
    public ConvocatoriaNotFoundException() {
        super("No hay convocatoria con ese id de búsqueda");
    }
}
