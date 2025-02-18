package com.salesianostriana.ProyectoConecta.error;

public class ContactoNotFoundException extends RuntimeException {

    public ContactoNotFoundException(Long id) {
        super("Contacto " + id + " no encontrado");
    }
    public ContactoNotFoundException(String msg) {
        super(msg);
    }
    public ContactoNotFoundException() {
        super("No hay contacto con ese id de búsqueda");
    }
}
