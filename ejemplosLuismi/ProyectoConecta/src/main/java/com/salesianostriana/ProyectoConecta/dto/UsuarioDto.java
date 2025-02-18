package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Usuario;

public record UsuarioDto(String username,
                         String role) {

    public static UsuarioDto of (Usuario u){
       return new UsuarioDto(
               u.getUsername(),
               u.getRole());
    }

}
