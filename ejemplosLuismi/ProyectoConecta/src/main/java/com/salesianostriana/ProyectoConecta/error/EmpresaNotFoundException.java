package com.salesianostriana.ProyectoConecta.error;

public class EmpresaNotFoundException extends RuntimeException {

    public EmpresaNotFoundException(Long id) {
        super("Empresa " + id + " no encontrado");
    }
    public EmpresaNotFoundException(String msg) {
        super(msg);
    }
    public EmpresaNotFoundException() {
        super("No hay empresas con ese id de búsqueda");
    }
}
