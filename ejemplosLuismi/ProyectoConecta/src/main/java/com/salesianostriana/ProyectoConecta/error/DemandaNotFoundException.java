package com.salesianostriana.ProyectoConecta.error;

public class DemandaNotFoundException extends RuntimeException {

    public DemandaNotFoundException(Long id) {
        super("Demanda " + id + " no encontrada");
    }
    public DemandaNotFoundException(String msg) {
        super(msg);
    }
    public DemandaNotFoundException() {
        super("No hay demanda con ese id de búsqueda");
    }
}
