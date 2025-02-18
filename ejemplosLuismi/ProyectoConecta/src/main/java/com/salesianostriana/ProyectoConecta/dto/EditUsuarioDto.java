package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Usuario;

public record EditUsuarioDto(String username) {

    public static EditUsuarioDto of (Usuario u){
        return new EditUsuarioDto(u.getUsername());
    }
}
